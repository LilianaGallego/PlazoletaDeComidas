package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.exceptions.OwnerMustBeOfLegalAge;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.Period;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    @Value("${my.variables.minimum-age}")
    int minimumAge;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {

        validateAge(user);

    }

    @Override
    public void saveUserEmployee(User user) {

        validateAge(user);
    }

    @Override
    public void validateAge(User user){
        LocalDate localDate =  LocalDate.now();

        int age = Period.between(user.getBirthdate(),localDate).getYears();

        if (age < minimumAge ) {
            throw new OwnerMustBeOfLegalAge();
        }

        userPersistencePort.saveUser(user);

    }

    @Override
    public User getOwner(Long id) {

        return userPersistencePort.getOwner(id);
    }

    @Override
    public User getEmployee(String  dni) {

        return userPersistencePort.getEmployee(dni);
    }

}
