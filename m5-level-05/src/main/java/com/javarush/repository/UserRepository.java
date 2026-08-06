package com.javarush.repository;

import com.javarush.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // save, findById, findAll, delete, findAll(Sort), findAll(Pageable) ...

    // Производные запросы: реализация генерируется по имени метода
    Optional<User> findByEmail(String email);

    Optional<User> findByNameAndEmail(String username, String email);

    List<User> findByName(String name);

    /**
     * JPQL-запрос: оперирует именем класса User и именем поля email.
     * :domain - именованный параметр, связанный через @Param.
     */
    @Query("SELECT u FROM User u WHERE u.email LIKE %:domain")
    List<User> findUsersByEmailDomain(@Param("domain") String domain);

    /**
     * Нативный SQL: оперирует именем таблицы USERS и именами колонок.
     * Modifying обязателен для UPDATE и DELETE, вызов требует транзакции.
     */
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE USERS SET name = UPPER(name) WHERE email LIKE :domain",
            nativeQuery = true)
    int upperCaseNamesByDomain(@Param("domain") String domain);
}
