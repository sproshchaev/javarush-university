package com.javarush.service;

import com.javarush.dto.PageDto;
import com.javarush.entity.User;
import com.javarush.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Внедрение через конструктор: аннотация Autowired не нужна
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAllSorted() {
        // findAll(Sort) - метод из JpaRepository, тело писать не нужно
        return userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    @Transactional(readOnly = true)
    public PageDto findPage(int page, int size) {
        Page<User> result = userRepository.findAll(PageRequest.of(page, size));
        return new PageDto(
                result.getContent(),
                result.getNumber(),
                result.getTotalPages(),
                result.getTotalElements()
        );
    }

    @Transactional(readOnly = true)
    public List<User> findByEmailDomain(String domain) {
        return userRepository.findUsersByEmailDomain(domain);
    }

    @Transactional
    public int upperCaseNamesByDomain(String domain) {
        // Изменяющий запрос обязан выполняться внутри транзакции.
        // Знак процента добавляем к значению, поскольку запрос нативный
        return userRepository.upperCaseNamesByDomain("%" + domain);
    }
}
