package com.javarush.service;

import com.javarush.entity.User;
import com.javarush.exception.RegistrationException;
import com.javarush.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    /**
     * Бин TransactionTemplate создаётся Spring Boot автоматически,
     * если в контексте есть единственный PlatformTransactionManager.
     */
    private final TransactionTemplate transactionTemplate;

    public void registerUser(String name, String email, boolean fail) {
        transactionTemplate.execute(status -> {
            // Этот код будет выполнен в транзакции
            try {
                User user = new User(name, email);
                userRepository.save(user);
                sendConfirmationEmail(user, fail);
            } catch (Exception e) {
                // Вручную помечаем транзакцию для отката
                status.setRollbackOnly();
                throw new RegistrationException("Registration failed", e);
            }
            return null; // Возвращаемое значение
        });
    }

    private void sendConfirmationEmail(User user, boolean fail) {
        System.out.println("Sending email to " + user.getEmail());
        if (fail) {
            throw new RuntimeException("Email service unavailable");
        }
    }
}
