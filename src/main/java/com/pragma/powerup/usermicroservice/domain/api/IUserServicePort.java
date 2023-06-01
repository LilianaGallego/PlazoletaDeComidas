package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;


public interface IUserServicePort {
    void saveUser(User user);

    String saveUserEmployee(User user);
    void validateAge(User user);
    User getOwner(Long id);

    User getEmployee(String dni);

}
