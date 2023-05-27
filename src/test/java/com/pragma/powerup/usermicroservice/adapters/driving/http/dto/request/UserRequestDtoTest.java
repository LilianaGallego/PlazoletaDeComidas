package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestDtoTest {
    @Test
    void testGetters() {
        UserRequestDto userRequestDto = new UserRequestDto("Lili", "Gallego","lili@gmail.com","288383", LocalDate.of(1989, 3, 4),"12345","123456", 1L);

        assertEquals("Lili", userRequestDto.getName());
        assertEquals("Gallego", userRequestDto.getSurname());
        assertEquals("lili@gmail.com", userRequestDto.getMail());
        assertEquals("288383", userRequestDto.getPhone());
        assertEquals(LocalDate.of(1989, 3, 4), userRequestDto.getBirthdate());
        assertEquals("12345", userRequestDto.getDniNumber());
        assertEquals("123456", userRequestDto.getPassword());
        assertEquals(1L, userRequestDto.getIdRole());
    }

    @Test
    void testSetters() {
        // Arrange
        String name = "Lili";
        String surname = "Gallego";
        String mail = "lili@gmail.com";
        String phone = "+1234567890";
        LocalDate birthdate = LocalDate.of(2000, 1, 1);
        String dniNumber = "123456789";
        String password = "password";
        Long idRole = 1L;

        UserRequestDto userRequestDto = new UserRequestDto();

        // Act
        userRequestDto.setName(name);
        userRequestDto.setSurname(surname);
        userRequestDto.setMail(mail);
        userRequestDto.setPhone(phone);
        userRequestDto.setBirthdate(birthdate);
        userRequestDto.setDniNumber(dniNumber);
        userRequestDto.setPassword(password);
        userRequestDto.setIdRole(idRole);

        // Assert
        assertEquals(name, userRequestDto.getName());
        assertEquals(surname, userRequestDto.getSurname());
        assertEquals(mail, userRequestDto.getMail());
        assertEquals(phone, userRequestDto.getPhone());
        assertEquals(birthdate, userRequestDto.getBirthdate());
        assertEquals(dniNumber, userRequestDto.getDniNumber());
        assertEquals(password, userRequestDto.getPassword());
        assertEquals(idRole, userRequestDto.getIdRole());
    }

}