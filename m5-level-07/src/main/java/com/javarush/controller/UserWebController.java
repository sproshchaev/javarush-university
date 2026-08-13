package com.javarush.controller;

import com.javarush.entity.User;
import com.javarush.exception.ResourceNotFoundException;
import com.javarush.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users-web")
@RequiredArgsConstructor
public class UserWebController {

    private final UserRepository userRepository;

    // Вызывается ПЕРЕД каждым методом этого контроллера.
    // Результат кладётся в модель под ключом "totalUsers".
    @ModelAttribute("totalUsers")
    public long totalUsers() {
        return userRepository.count();
    }

    @GetMapping("/list") // localhost:8080/users-web/list
    public String listUsers(@RequestParam(value = "name", required = false) String name, Model model) {

        List<User> userList;
        if (name != null && !name.isEmpty()) {
            userList = userRepository.findByName(name);
            model.addAttribute("searchName", name);
        } else {
            userList = userRepository.findAll();
        }
        model.addAttribute("users", userList);
        return "user-list"; // user-list.html
    }

    @GetMapping("/view/{id}") // localhost:8080/users-web/view/1
    public String viewUserDetails(@PathVariable("id") Long id, Model model) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Пользователь с таким id не найден: " + id));
        model.addAttribute("user", user);
        return "user-details"; // user-details.html
    }

    // Шаг 1: показать пустую форму
    @GetMapping("/new") // localhost:8080/users-web/new
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user-form"; // user-form.html
    }

    // Шаг 2: принять заполненную форму
    @PostMapping("/create")
    public String processCreateForm(@ModelAttribute("user") User user) {
        userRepository.save(user);
        return "redirect:/users-web/list";
    }

    // Локальный обработчик: ловит исключение только из методов ЭТОГО контроллера
    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error-404";
    }

}
