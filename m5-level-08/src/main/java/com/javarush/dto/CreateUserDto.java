package com.javarush.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDto {

    // Тексты сообщений лежат в ValidationMessages.properties,
    // здесь указан только ключ в фигурных скобках
    @NotBlank(message = "{user.name.notblank}")
    @Size(min = 2, max = 100, message = "{user.name.size}")
    private String name;

    @NotBlank(message = "{user.email.notblank}")
    @Email(message = "{user.email.format}")
    private String email;

    // Обёртка Integer, а не примитив int: пустое поле формы должно стать null,
    // иначе @NotNull не сработает никогда
    @NotNull(message = "{user.age.notnull}")
    @Min(value = 18, message = "{user.age.min}")
    @Max(value = 120, message = "{user.age.max}")
    private Integer age;

    // @Pattern пропускает null: поле необязательное
    @Pattern(regexp = "\\+7[0-9]{10}", message = "{user.phone.pattern}")
    private String phone;

}
