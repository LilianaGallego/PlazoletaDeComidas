package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.MailAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.UserAlreadyExistsException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IUserEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;

class UserMysqlAdapterTest {
    @Mock
    private IUserRepository userRepository;
    @Mock
    private IUserEntityMapper userEntityMapper;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserMysqlAdapter userMysqlAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Given a user when saveUser then save the user in the repository")
    public void saveUserTest() {
        // Arrange
        User user = new User(1L, "Lili", "Gallego","lili@gmail.com","288383",
                LocalDate.of(1989, 3, 4),"12345","123456",
                new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));

        UserEntity userEntity = new UserEntity(1L, "Lili", "Gallego","lili@gmail.com",
                "288383", LocalDate.of(1989, 3, 4),"12345","123456",
                new RoleEntity(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));
        Mockito.when(userRepository.findByDniNumber(user.getDniNumber())).thenReturn(Optional.empty());
        Mockito.when(userRepository.existsByMail(user.getMail())).thenReturn(false);
        Mockito.when(userEntityMapper.toEntity(user)).thenReturn(userEntity);

        // Act
        userMysqlAdapter.saveUserOwner(user);

        // Assert
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    @DisplayName("Given an existing user dni number when saveUser then throw UserAlreadyExistsException")
    public void saveUserWithExistingDniNumberTest() {
        // Arrange

        User user = new User(1L, "Lili", "Gallego","lili@gmail.com","288383",
                LocalDate.of(1989, 3, 4),"12345","123456",
                new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));
        when(userRepository.findByDniNumber(user.getDniNumber())).thenReturn(Optional.of(new UserEntity()));

        // Act & Assert
        Assertions.assertThrows(UserAlreadyExistsException.class, () -> userMysqlAdapter.saveUserOwner(user));
    }

    @Test
    @DisplayName("Given an existing user email when saveUser then throw MailAlreadyExistsException")
    public void saveUserWithExistingEmailTest() {
        // Arrange
        User user = new User(1L, "Lili", "Gallego","lili@gmail.com","288383",
                LocalDate.of(1989, 3, 4),"12345","123456",
                new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));
        when(userRepository.findByDniNumber(user.getDniNumber())).thenReturn(Optional.empty());
        when(userRepository.existsByMail(user.getMail())).thenReturn(true);

        // Act & Assert
        Assertions.assertThrows(MailAlreadyExistsException.class, () -> userMysqlAdapter.saveUserOwner(user));
    }
}