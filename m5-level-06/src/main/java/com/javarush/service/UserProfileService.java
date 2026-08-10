package com.javarush.service;

import com.javarush.entity.User;
import com.javarush.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserRepository userRepository;
    private final AuditService auditService;

    /**
     * Внешняя транзакция для демонстрации propagation.
     * AuditService - отдельный бин, поэтому оба вызова проходят через прокси.
     */
    @Transactional
    public void renameUser(Long id, String newName, boolean fail) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.setName(newName);
        userRepository.save(user);

        // Две записи в журнал: одна присоединится к нашей транзакции, вторая пойдёт в свою
        auditService.logRequired("Переименование пользователя " + id);
        auditService.logRequiresNew("Переименование пользователя " + id);

        if (fail) {
            throw new RuntimeException("Сбой после записи в журнал");
        }
    }
}
