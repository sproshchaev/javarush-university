package com.javarush.starter;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.DispatcherServlet;

@AutoConfiguration
@ConditionalOnClass(DispatcherServlet.class)   // только для веб-приложений
public class GreetingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean                   // только если разработчик не создал свой
    public GreetingFormatter greetingFormatter() {
        return message -> "[auto] " + message;
    }
}
