package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.domain.model.User;

import java.util.Optional;

public interface IUserServicePort {
    void saveUserOwner(User user);
    void validateAge(User user);
    User getOwner(Long id);
}
