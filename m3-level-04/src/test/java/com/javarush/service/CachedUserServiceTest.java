package com.javarush.service;

import com.javarush.repository.UserRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CachedUserServiceTest {

    @Mock
    UserRepository repository;

    @InjectMocks
    CachedUserService service;

    @Test
    @DisplayName("Три запроса одного пользователя: база вызвана один раз")
    void getName_RepeatedCalls_QueriesRepositoryOnce() {
        // Создаем
        when(repository.findNameById(7)).thenReturn("Анна");

        // Вызываем и проверяем
        assertEquals("Анна", service.getName(7));
        assertEquals("Анна", service.getName(7));
        assertEquals("Анна", service.getName(7));

        verify(repository, times(1)).findNameById(7);
        verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName("Пользователя нет: ответ null тоже запоминается")
    void getName_UnknownUser_CachesNull() {
        assertNull(service.getName(8));
        assertNull(service.getName(8));

        verify(repository, times(1)).findNameById(8);
    }
}
