package com.javarush.controller;

import com.javarush.entity.AuditLog;
import com.javarush.exception.BusinessException;
import com.javarush.repository.AuditLogRepository;
import com.javarush.service.RegistrationService;
import com.javarush.service.UserProfileService;
import com.javarush.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер для демонстрации на вебинаре.
 * Все методы сделаны GET сознательно - чтобы вызывать их из закладок браузера.
 * В боевом коде изменяющие операции должны быть POST.
 */
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    private final UserService userService;
    private final UserProfileService userProfileService;
    private final RegistrationService registrationService;
    private final AuditLogRepository auditLogRepository;

    // Пример 1: откат на unchecked-исключении
    @GetMapping("/tx/update-emails")
    public String updateEmails(@RequestParam Long id1,
                               @RequestParam Long id2,
                               @RequestParam String email1,
                               @RequestParam String email2,
                               @RequestParam(defaultValue = "false") boolean fail) {
        try {
            userService.updateUserEmails(id1, id2, email1, email2, fail);
            return "OK: оба адреса сохранены";
        } catch (RuntimeException e) {
            return "Исключение: " + e.getMessage() + " -> ожидаем ROLLBACK";
        }
    }

    // Пример 2: checked-исключение, транзакция фиксируется
    @GetMapping("/tx/checked")
    public String checked(@RequestParam Long id, @RequestParam String email) {
        try {
            userService.updateUserEmailWithChecked(id, email);
            return "OK";
        } catch (BusinessException e) {
            return "Исключение: " + e.getMessage() + " -> транзакция всё равно COMMIT";
        }
    }

    // Пример 3: то же самое с rollbackFor
    @GetMapping("/tx/checked-rollback")
    public String checkedRollback(@RequestParam Long id, @RequestParam String email) {
        try {
            userService.updateUserEmailWithCheckedRollback(id, email);
            return "OK";
        } catch (BusinessException e) {
            return "Исключение: " + e.getMessage() + " -> теперь ROLLBACK";
        }
    }

    // Пример 4: REQUIRED против REQUIRES_NEW
    @GetMapping("/tx/rename")
    public String rename(@RequestParam Long id,
                         @RequestParam String name,
                         @RequestParam(defaultValue = "false") boolean fail) {
        try {
            userProfileService.renameUser(id, name, fail);
            return "OK: имя изменено";
        } catch (RuntimeException e) {
            return "Исключение: " + e.getMessage() + " -> смотрим журнал аудита";
        }
    }

    @GetMapping("/audit")
    public List<AuditLog> audit() {
        return auditLogRepository.findAll();
    }

    // Пример 5: readOnly и dirty checking
    @GetMapping("/tx/read-only")
    public String readOnly(@RequestParam Long id) {
        return userService.tryUpdateInReadOnly(id);
    }

    @GetMapping("/tx/dirty-checking")
    public String dirtyChecking(@RequestParam Long id) {
        return userService.updateWithDirtyChecking(id);
    }

    // Пример 6: самовызов через this и внешний вызов
    @GetMapping("/tx/self-invocation")
    public String selfInvocation(@RequestParam Long id, @RequestParam String email) {
        try {
            userService.selfInvocation(id, email);
            return "OK";
        } catch (RuntimeException e) {
            return "Исключение: " + e.getMessage() + " -> транзакции не было, данные сохранены";
        }
    }

    @GetMapping("/tx/external-call")
    public String externalCall(@RequestParam Long id, @RequestParam String email) {
        try {
            userService.updateEmailInTransaction(id, email);
            return "OK";
        } catch (RuntimeException e) {
            return "Исключение: " + e.getMessage() + " -> прокси сработал, ROLLBACK";
        }
    }

    // Пример 7: TransactionTemplate
    @GetMapping("/tx/register")
    public String register(@RequestParam String name,
                           @RequestParam String email,
                           @RequestParam(defaultValue = "false") boolean fail) {
        try {
            registrationService.registerUser(name, email, fail);
            return "OK: пользователь зарегистрирован";
        } catch (RuntimeException e) {
            return "Исключение: " + e.getMessage() + " -> setRollbackOnly сработал";
        }
    }
}
