package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl.UserHandlerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {

    @Mock
    private IUserHandler userHandler;

    @InjectMocks
    private UserRestController userRestController;

    @InjectMocks
    private UserRequestDto userRequestDto;

    @BeforeEach
    void setUp() {
       userRequestDto = new UserRequestDto("Lili", "Gallego","lili@gmail.com","288383", LocalDate.of(1989, 3, 4),"12345","123456", 1L);
    }

    @Test
    void testSaveUserOwner() {
        // Arrange
        Mockito.doNothing().when(userHandler).saveUserOwner(userRequestDto);

        // Act
        ResponseEntity<Map<String, String>> responseEntity = userRestController.saveUserOwner(userRequestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(Constants.USER_CREATED_MESSAGE, responseEntity.getBody().get(Constants.RESPONSE_MESSAGE_KEY));
    }

    @Test
    void testSaveUserEmployee() {
        // Arrange
        UserRequestDto userRequestDto = new UserRequestDto();
        // Set the necessary fields in the userRequestDto object

        UserHandlerImpl userHandler = mock(UserHandlerImpl.class);
        when(userHandler.saveUserEmployee(any(UserRequestDto.class))).thenReturn("123456789");

        UserRestController userRestController = new UserRestController(userHandler);

        // Act
        ResponseEntity<String> response = userRestController.saveUserEmployee(userRequestDto);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("123456789", response.getBody());
        // Add additional assertions or verifications if needed
    }

    @Test
    void testGetOwnerById() throws Exception {
        // Arrange
        Long ownerId = 1L;

        UserResponseDto responseDto = new UserResponseDto(

                "Lili", "Gallego", "lili@gmail.com", "288383",
                LocalDate.of(1989, 3, 4), "12345", "123456", 1L
        );

        when(userHandler.getOwner(ownerId)).thenReturn(responseDto);

        // Act
        ResponseEntity<UserResponseDto> response = userRestController.getOwnerById(ownerId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(userHandler, times(1)).getOwner(ownerId);
    }

}