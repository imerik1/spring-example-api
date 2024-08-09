package com.ekerdev.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ekerdev.application.dto.UserDTO;
import com.ekerdev.application.entity.User;
import com.ekerdev.application.exception.UserNotFoundException;
import com.ekerdev.application.repository.UserRepository;
import com.ekerdev.application.service.impl.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    private final Long ID = 1000L;
    private final String NAME = "zezinho";
    private final String EMAIL = "zezinho@gmail.com";

    @Test
    void shouldReturnUserById() {
        User user = new User(ID, EMAIL, NAME);
        when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(user));

        UserDTO userDTO = userService.getById(ID);

        assertEquals(userDTO.getId(), ID);
        assertEquals(userDTO.getEmail(), EMAIL);
        assertEquals(userDTO.getName(), NAME);
    }

    @Test
    void shouldReturnUserByEmail() {
        User user = new User(ID, EMAIL, NAME);
        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.ofNullable(user));

        UserDTO userDTO = userService.getByEmail(EMAIL);

        assertEquals(userDTO.getId(), ID);
        assertEquals(userDTO.getEmail(), EMAIL);
        assertEquals(userDTO.getName(), NAME);
    }

    @Test
    void shouldThrowWhenUserNotFoundById() {
        when(userRepository.findById(ID)).thenReturn(Optional.ofNullable(null));

        assertThrows(UserNotFoundException.class, () -> {
            userService.getById(ID);
        });
    }

    @Test
    void shouldThrowWhenUserNotFoundByEmail() {
        when(userRepository.findByEmail(EMAIL)).thenReturn(Optional.ofNullable(null));

        assertThrows(UserNotFoundException.class, () -> {
            userService.getByEmail(EMAIL);
        });
    }
}
