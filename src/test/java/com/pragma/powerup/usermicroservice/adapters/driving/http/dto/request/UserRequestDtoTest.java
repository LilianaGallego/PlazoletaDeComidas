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
}