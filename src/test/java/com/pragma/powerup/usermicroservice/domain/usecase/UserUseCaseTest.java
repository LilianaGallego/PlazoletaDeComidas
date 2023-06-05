package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.exceptions.OwnerMustBeOfLegalAge;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;
    private UserUseCase userUseCase;
    private LocalDate currentDate;
    @Mock
    private IUserHandler userHandler;
    private int minimumAge;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userUseCase = new UserUseCase(userPersistencePort);
        currentDate = LocalDate.now();
        minimumAge = 18;
        userUseCase.minimumAge = minimumAge;
    }

    @Test
    void saveUserOwner_validOwnerUser_callsPersistencePort() {
        // Arrange
        User user = new User(1L, "Lili", "Gallego","lili@gmail.com","288383",
                LocalDate.of(1989, 3, 4),"12345","123456",
                new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));

        // Act
        userUseCase.saveUser(user);

        // Assert
        Mockito.verify(userPersistencePort).saveUser(user);
    }


    @Test
    void validateAge_validOwnerAge_callsPersistencePort() {
        // Arrange
        User user = new User(1L, "Lili", "Gallego","lili@gmail.com","288383",
                LocalDate.of(1989, 3, 4),"12345","123456",
                new Role(Constants.OWNER_ROLE_ID, "ROLE","ROLE"));

        // Act
        userUseCase.validateAge(user);

        // Assert
        Mockito.verify(userPersistencePort).saveUser(user);
    }

    @Test
    void validateAge_invalidOwnerAge_throwsOwnerMustBeOfLegalAge() {
        // Arrange
        User user = new User(1L, "John", "Doe","John@gmail.com","288383",
                LocalDate.of(2020, 1, 1),"9239292","299293",
                new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));


        // Act & Assert
        assertThrows(OwnerMustBeOfLegalAge.class, () -> userUseCase.validateAge(user));
    }

    @Test
    void testGetOwner() {
        // Arrange
        Long ownerId = 1L;
        User user = new User(1L, "John", "Doe","John@gmail.com","288383",
                LocalDate.of(2020, 1, 1),"9239292","299293",
                new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));

        // Define the behavior of the userPersistencePort mock
        Mockito.when(userPersistencePort.getOwner(ownerId)).thenReturn(user);

        // Act
        User result = userUseCase.getOwner(user.getId());

        // Assert
        assertEquals(user, result);
        Mockito.verify(userPersistencePort, Mockito.times(1)).getOwner(ownerId);
    }

    @Test
    void saveUserEmployee_validEmployeerUser_callsPersistencePort() {
        // Arrange
        User user = new User(1L, "Lili", "Gallego","lili@gmail.com","288383",
                LocalDate.of(1989, 3, 4),"12345","123456",
                new Role(Constants.EMPLOYEE_ROLE_ID, "ROLE_EMPLOYEE","ROLE_EMPLOYEE"));

        // Act
        userUseCase.saveUserEmployee(user);

        // Assert
        Mockito.verify(userPersistencePort).saveUser(user);
    }


    @Test
    void validateAge_validEmployeeAge_callsPersistencePort() {
        // Arrange
        User user = new User(1L, "Lili", "Gallego","lili@gmail.com","288383",
                LocalDate.of(1989, 3, 4),"12345","123456",
                new Role(Constants.EMPLOYEE_ROLE_ID, "ROLE","ROLE"));

        // Act
        userUseCase.validateAge(user);

        // Assert
        Mockito.verify(userPersistencePort).saveUser(user);
    }

    @Test
    void validateAge_invalidEmployeeAge_throwsOwnerMustBeOfLegalAge() {
        // Arrange
        User user = new User(1L, "John", "Doe","John@gmail.com","288383",
                LocalDate.of(2020, 1, 1),"9239292","299293",
                new Role(Constants.EMPLOYEE_ROLE_ID, "ROLE_EMPLOYEE","ROLE_EMPLOYEE"));


        // Act & Assert
        assertThrows(OwnerMustBeOfLegalAge.class, () -> userUseCase.validateAge(user));
    }

    @Test
    void testGetEmployee() {
        // Arrange
        String dni = "9239292";
        User user = new User(1L, "John", "Doe","John@gmail.com","288383",
                LocalDate.of(2020, 1, 1),"9239292","299293",
                new Role(Constants.EMPLOYEE_ROLE_ID, "ROLE_EMPLOYEE","ROLE_EMPLOYEE"));

        // Define the behavior of the userPersistencePort mock
        Mockito.when(userPersistencePort.getEmployee(dni)).thenReturn(user);

        // Act
        User result = userUseCase.getEmployee(user.getDniNumber());

        // Assert
        assertEquals(user, result);
        Mockito.verify(userPersistencePort, Mockito.times(1)).getEmployee(dni);
    }


}