package com.javarush.controller;

import com.javarush.dto.CreateUserDto;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Демонстрационный контроллер: показывает поведение по умолчанию,
// когда параметр BindingResult отсутствует
@Controller
@RequestMapping("/users-unsafe")
public class UnvalidatedUserController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("userDto", new CreateUserDto());
        return "create-user-unsafe";
    }

    @PostMapping("/create")
    public String createUser(@Valid @ModelAttribute("userDto") CreateUserDto userDto) {
        // До этой строки выполнение не дойдёт, если проверка провалилась
        System.out.println("Метод выполняется, данные корректны: " + userDto);
        return "redirect:/users-web/list";
    }
}
