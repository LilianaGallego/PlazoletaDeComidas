package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserResponseMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import static org.mockito.Mockito.*;


class UserHandlerImplTest {
    private UserHandlerImpl userHandler;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IUserRequestMapper userRequestMapper;

    @Mock
    private IUserResponseMapper userResponseMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userHandler = new UserHandlerImpl(userServicePort, userRequestMapper, userResponseMapper);
    }

    @Test
    void saveUser_ShouldCallSaveUserInServicePort() {
        // Arrange
        UserRequestDto userRequestDto = new UserRequestDto("Lili", "Gallego","lili@gmail.com","288383", LocalDate.of(1989, 3, 4),"12345","123456", 1L);
        User user = new User();
        Mockito.when(userRequestMapper.toUser(userRequestDto)).thenReturn(user);

        // Act
        userHandler.saveUserOwner(userRequestDto);

        // Assert
        verify(userServicePort, times(1)).saveUser(user);
    }
}