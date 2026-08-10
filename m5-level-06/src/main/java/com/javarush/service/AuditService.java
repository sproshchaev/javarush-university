package com.javarush.service;

import com.javarush.entity.AuditLog;
import com.javarush.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    /**
     * Режим по умолчанию: метод присоединяется к транзакции вызывающего кода
     * и разделяет её судьбу - откат внешней транзакции удалит и эту запись.
     */
    @Transactional
    public void logRequired(String message) {
        auditLogRepository.save(new AuditLog("[REQUIRED] " + message));
    }

    /**
     * Собственная транзакция: фиксируется независимо от исхода внешней.
     * Внешняя транзакция на время выполнения приостанавливается.
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logRequiresNew(String message) {
        auditLogRepository.save(new AuditLog("[REQUIRES_NEW] " + message));
    }
}
