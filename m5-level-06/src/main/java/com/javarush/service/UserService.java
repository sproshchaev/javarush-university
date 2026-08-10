package com.javarush.service;

import com.javarush.entity.User;
import com.javarush.exception.BusinessException;
import com.javarush.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // ---------- Пример 1: откат на unchecked-исключении ----------

    @Transactional
    public void updateUserEmails(Long id1, Long id2, String email1, String email2, boolean fail) {

        User user1 = userRepository.findById(id1)
                .orElseThrow(() -> new RuntimeException("User not found: " + id1));
        User user2 = userRepository.findById(id2)
                .orElseThrow(() -> new RuntimeException("User not found: " + id2));

        user1.setEmail(email1);
        user2.setEmail(email2);

        userRepository.save(user1);
        userRepository.save(user2);

        // Эмуляция сбоя между двумя сохранениями и завершением метода
        if (fail) {
            throw new RuntimeException("Что-то пошло не так!");
        }
    }

    // ---------- Пример 2: checked-исключение, поведение по умолчанию ----------

    @Transactional
    public void updateUserEmailWithChecked(Long id, String newEmail) throws BusinessException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.setEmail(newEmail);
        userRepository.save(user);

        // Бизнес-ошибка после сохранения: проверяемое исключение
        throw new BusinessException("Business error after update");
    }

    // ---------- Пример 3: то же самое с rollbackFor ----------

    @Transactional(rollbackFor = BusinessException.class)
    public void updateUserEmailWithCheckedRollback(Long id, String newEmail) throws BusinessException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.setEmail(newEmail);
        userRepository.save(user);

        throw new BusinessException("Business error after update");
    }

    // ---------- Пример 5: readOnly и dirty checking ----------

    @Transactional(readOnly = true)
    public String tryUpdateInReadOnly(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        // Метод save() не вызывается сознательно
        user.setName(user.getName() + "_readOnly");
        return "Имя объекта в памяти: " + user.getName();
    }

    @Transactional
    public String updateWithDirtyChecking(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        // Метод save() не вызывается и здесь
        user.setName(user.getName() + "_dirty");
        return "Имя объекта в памяти: " + user.getName();
    }

    // ---------- Пример 6: самовызов через this ----------

    /**
     * Аннотации нет сознательно.
     * Вызов через this идёт мимо прокси, поэтому транзакция не открывается.
     */
    public void selfInvocation(Long id, String newEmail) {
        this.updateEmailInTransaction(id, newEmail);
    }

    @Transactional
    public void updateEmailInTransaction(Long id, String newEmail) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found: " + id));
        user.setEmail(newEmail);
        userRepository.save(user);

        throw new RuntimeException("Ошибка после сохранения");
    }

    // ---------- Служебный метод ----------

    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
