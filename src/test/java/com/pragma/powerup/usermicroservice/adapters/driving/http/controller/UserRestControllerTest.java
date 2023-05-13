package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class UserRestControllerTest {

    @Mock
    private IUserHandler userHandler;

    @InjectMocks
    private UserRestController userRestController;

    private UserRequestDto userRequestDto;

    @BeforeEach
    void setUp() {
        UserRequestDto userRequestDto = new UserRequestDto("Lili", "Gallego","lili@gmail.com","288383", LocalDate.of(1989, 3, 4),"12345","123456", 1L);
    }

    @Test
    @DisplayName("Given a valid user, when saveUser is called, then a CREATED response is returned")
    void testSaveUser() {
        // Arrange
        Mockito.doNothing().when(userHandler).saveUserOwner(userRequestDto);

        // Act
        ResponseEntity<Map<String, String>> responseEntity = userRestController.saveUserOwner(userRequestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(Constants.USER_CREATED_MESSAGE, responseEntity.getBody().get(Constants.RESPONSE_MESSAGE_KEY));
    }
}