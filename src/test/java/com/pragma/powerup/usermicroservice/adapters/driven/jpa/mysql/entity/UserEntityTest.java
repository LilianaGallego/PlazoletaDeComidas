package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {
    @Test
    void testGetters() {
        //Arrange
        UserEntity userEntity = new UserEntity(1L, "Lili", "Gallego","lili@gmail.com",
                "288383", LocalDate.of(1989, 3, 4),"12345","123456",
                new RoleEntity(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));
        // Act & Assert
        assertEquals(1L, userEntity.getId());
        assertEquals("Lili", userEntity.getName());
        assertEquals("Gallego", userEntity.getSurname());
        assertEquals("lili@gmail.com", userEntity.getMail());
        assertEquals("288383", userEntity.getPhone());
        assertEquals(LocalDate.of(1989, 3, 4), userEntity.getBirthdate());
        assertEquals("12345", userEntity.getDniNumber());
        assertEquals("123456", userEntity.getPassword());
        assertEquals(2L, userEntity.getRoleEntity().getId());
    }

    @Test
    void testSetters() {
        //Arrange
        UserEntity userEntity = new UserEntity();

        //Act
        userEntity.setId(1L);
        userEntity.setName("Lili");
        userEntity.setSurname("Gallego");
        userEntity.setMail("lili@gmail.com");
        userEntity.setPhone("288383");
        userEntity.setDniNumber("12345");
        userEntity.setPassword("123456");
        userEntity.setBirthdate(LocalDate.of(1989, 3, 4));
        userEntity.setRoleEntity(new RoleEntity(1L, "ADMIN", "ADMIN"));

        //Assert
        assertEquals(1L, userEntity.getId());
        assertEquals("Lili", userEntity.getName());
        assertEquals("Gallego", userEntity.getSurname());
        assertEquals("lili@gmail.com", userEntity.getMail());
        assertEquals("288383", userEntity.getPhone());
        assertEquals(LocalDate.of(1989, 3, 4), userEntity.getBirthdate());
        assertEquals("12345", userEntity.getDniNumber());
        assertEquals("123456", userEntity.getPassword());
        assertEquals(1L, userEntity.getRoleEntity().getId());

    }
}