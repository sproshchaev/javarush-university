package com.javarush.demo.greeting;

import com.javarush.starter.GreetingFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Пример 4: ручной бин побеждает автоконфигурацию.
 * Как только этот бин есть в контексте, @ConditionalOnMissingBean в
 * GreetingAutoConfiguration молчит, и /greeting возвращает этот формат (>>> ... <<<)
 * вместо автоматического [auto] ... .
 * Закомментируйте класс целиком, чтобы вернуться к примеру 3 с префиксом [auto].
 */
@Configuration
public class CustomFormatterConfig {

    @Bean
    public GreetingFormatter greetingFormatter() {
        return message -> ">>> " + message + " <<<";
    }
}
