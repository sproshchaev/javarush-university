package com.javarush.repository;

import com.javarush.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Срез приложения: поднимается только JPA-слой.
 * Контроллеры и сервисы в контекст не попадают.
 * Каждый тест выполняется в транзакции, которая откатывается после его завершения.
 *
 * Порядок методов зафиксирован через @Order: последний тест проверяет,
 * что данные первого теста откатились, и без явного порядка эта проверка
 * потеряла бы смысл (JUnit по умолчанию сортирует методы сам).
 */
@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserRepositoryDataJpaTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager; // Утилита для подготовки данных

    @Order(1)
    @Test
    @DisplayName("Должен найти пользователя по email, если он сохранён")
    void findByEmail_whenUserExists_shouldReturnUser() {

        // Arrange: Сохраняем тестовые данные
        entityManager.persist(new User("Test User", "test.user@example.com"));

        // Act: Вызываем метод репозитория
        Optional<User> found = userRepository.findByEmail("test.user@example.com");

        // Assert: Проверяем результат
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test User");
    }

    @Order(2)
    @Test
    @DisplayName("Должен вернуть пустой Optional для несуществующего email")
    void findByEmail_whenUserNotExists_shouldReturnEmpty() {

        Optional<User> found = userRepository.findByEmail("nobody@example.com");

        assertThat(found).isEmpty();
    }

    @Order(3)
    @Test
    @DisplayName("Должен вернуть список пользователей с одинаковым именем")
    void findByName_shouldReturnList() {

        entityManager.persist(new User("Duplicate", "dup1@example.com"));
        entityManager.persist(new User("Duplicate", "dup2@example.com"));

        List<User> found = userRepository.findByName("Duplicate");

        assertThat(found).hasSize(2);
    }

    @Order(4)
    @Test
    @DisplayName("Данные предыдущих тестов откатываются: сохранённого пользователя нет")
    void previousTestData_shouldBeRolledBack() {

        Optional<User> found = userRepository.findByEmail("test.user@example.com");

        assertThat(found).isEmpty();
    }

}
