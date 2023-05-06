package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.exceptions.OwnerMustBeOfLegalAge;
import com.pragma.powerup.usermicroservice.domain.exceptions.RoleNotCreated;
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
    private int minimumAge;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userUseCase = new UserUseCase(userPersistencePort);
        currentDate = LocalDate.now();
        minimumAge = 18;
        userUseCase.minimumAge = minimumAge;
    }

    @Test
    public void saveUserOwner_validOwnerUser_callsPersistencePort() {
        // Arrange
        User user = new User(1L, "Lili", "Gallego","lili@gmail.com","288383",
                LocalDate.of(1989, 3, 4),"12345","123456",
                new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));

        // Act
        userUseCase.saveUserOwner(user);

        // Assert
        Mockito.verify(userPersistencePort).saveUserOwner(user);
    }

    @Test
    public void saveUserOwner_invalidRoleUser_throwsRoleNotCreated() {
        // Arrange
        User user = new User(1L, "Lili", "Gallego","lili@gmail.com","288383", LocalDate.of(1989, 3, 4),"12345","123456", new Role(Constants.ADMIN_ROLE_ID, "ROLE_ADMIN","ROLE_ADMIN"));


        // Act & Assert
        assertThrows(RoleNotCreated.class, () -> userUseCase.saveUserOwner(user));
    }

    @Test
    public void validateAge_validOwnerAge_callsPersistencePort() {
        // Arrange
        User user = new User(1L, "Lili", "Gallego","lili@gmail.com","288383", LocalDate.of(1989, 3, 4),"12345","123456", new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));



        // Act
        userUseCase.validateAge(user);

        // Assert
        Mockito.verify(userPersistencePort).saveUserOwner(user);
    }

    @Test
    public void validateAge_invalidOwnerAge_throwsOwnerMustBeOfLegalAge() {
        // Arrange
        User user = new User(1L, "John", "Doe","John@gmail.com","288383", LocalDate.of(2020, 1, 1),"9239292","299293", new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));


        // Act & Assert
        assertThrows(OwnerMustBeOfLegalAge.class, () -> userUseCase.validateAge(user));
    }


}