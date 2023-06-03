package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;

public interface IUserHandler {
    void saveUserOwner(UserRequestDto userRequestDto);
    UserResponseDto getOwner(Long id);
    UserResponseDto getEmploye(String dni);
    void saveUserEmployee(UserRequestDto userRequestDto);

    void saveUserClient(UserRequestDto userRequestDto);

}
