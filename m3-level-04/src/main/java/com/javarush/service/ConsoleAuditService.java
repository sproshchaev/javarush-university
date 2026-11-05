package com.javarush.service;

public class ConsoleAuditService implements ExternalAuditService {

    @Override
    public void logUserCreation(String username, String userId) {
        System.out.println("[AUDIT] создан пользователь " + username);
    }

    @Override
    public void logUserDeletion(String userId) {
        System.out.println("[AUDIT] удален пользователь " + userId);
    }
}
