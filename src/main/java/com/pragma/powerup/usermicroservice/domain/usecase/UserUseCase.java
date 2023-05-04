package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.exceptions.OwnerMustBeOfLegalAge;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUserOwner(User user) {

        LocalDate localDate =  LocalDate.now();

        int age = Period.between(user.getBirthdate(),localDate).getYears();

        if (age >= 18 && user.getRole().getId() == Constants.OWNER_ROLE_ID) {
            userPersistencePort.saveUserOwner(user);
        }
        if (age < 18 && user.getRole().getId() == Constants.OWNER_ROLE_ID) {
            throw new OwnerMustBeOfLegalAge();

        }

    }

    @Override
    public void deleteUser(User user) {
        userPersistencePort.deleteUser(user);
    }

    @Override
    public List<User> getAllProviders(int page) {
        return userPersistencePort.getAllProviders(page);
    }

    @Override
    public User getProvider(Long id) {
        return userPersistencePort.getProvider(id);
    }

    @Override
    public User getEmployee(Long id) {
        return userPersistencePort.getEmployee(id);
    }

    @Override
    public User getClient(Long id) {
        return userPersistencePort.getClient(id);
    }
}
