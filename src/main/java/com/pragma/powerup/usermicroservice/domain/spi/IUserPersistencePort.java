package com.pragma.powerup.usermicroservice.domain.spi;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.domain.model.User;

import java.util.Optional;

public interface IUserPersistencePort {
    void saveUserOwner(User user);
    User getOwner(Long id);

}
