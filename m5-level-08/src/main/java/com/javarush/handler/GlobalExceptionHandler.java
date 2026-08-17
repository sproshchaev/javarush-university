package com.javarush.handler;

import com.javarush.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Ловит ResourceNotFoundException из ЛЮБОГО контроллера
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleResourceNotFound(ResourceNotFoundException ex, Model model) {
        log.warn("Resource not found: {}", ex.getMessage());
        model.addAttribute("errorMessage", ex.getMessage());
        return "error-404";
    }

    // Ловит всё остальное, что не перехвачено более специфичным обработчиком
    @ExceptionHandler(Exception.class)
    public String handleGenericException(Exception ex, WebRequest request, Model model) {
        // Логируем с уровнем ERROR, включая полный стектрейс (третий аргумент 'ex')
        log.error("Unhandled exception for request: {}", request.getDescription(false), ex);

        model.addAttribute("errorMessage", "Произошла непредвиденная ошибка. Попробуйте позже.");
        return "error-500";
    }
}
