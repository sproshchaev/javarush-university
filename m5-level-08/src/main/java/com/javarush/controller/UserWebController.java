package com.javarush.controller;

import com.javarush.dto.CreateUserDto;
import com.javarush.entity.User;
import com.javarush.exception.ResourceNotFoundException;
import com.javarush.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users-web")
@RequiredArgsConstructor
public class UserWebController {

    private final UserRepository userRepository;

    // Незаполненное поле формы приходит пустой строкой, а не null.
    // Превращаем пустые строки в null, иначе @Pattern на незаполненном телефоне даст ошибку.
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    // Отображение формы создания
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("userDto", new CreateUserDto());
        return "create-user";
    }

    // Обработка отправки формы
    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("userDto") CreateUserDto userDto,
                             BindingResult bindingResult) {   // <-- сразу после @Valid

        // Если есть ошибки валидации
        if (bindingResult.hasErrors()) {
            return "create-user";   // остаёмся на форме, ошибки уже в модели
        }

        // Ошибок нет — сохраняем пользователя
        User user = new User(userDto.getName(), userDto.getEmail(),
                             userDto.getAge(), userDto.getPhone());
        userRepository.save(user);
        return "redirect:/users-web/list";
    }

    // Список пользователей
    @GetMapping("/list")
    public String listUsers(@RequestParam(required = false) String name, Model model) {
        model.addAttribute("searchName", name);
        if (name != null && !name.isBlank()) {
            model.addAttribute("users", userRepository.findByName(name));
        } else {
            model.addAttribute("users", userRepository.findAll());
        }
        return "user-list";
    }

    @GetMapping("/{id}")
    public String getUserDetails(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с ID " + id + " не найден"));
        model.addAttribute("user", user);
        return "user-details";
    }

    // Локальный обработчик: работает ТОЛЬКО в этом контроллере.
    // Закомментируйте его, чтобы то же исключение подхватил GlobalExceptionHandler (демо 11).
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error-404";
    }

}
