package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.PrincipalUser;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByMail(email).orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        List<UserEntity> usersEntity = userRepository.findAllById(userEntity.getId());
        if (usersEntity.isEmpty()) {
            throw new UsernameNotFoundException("User not found with Email: " + email);
        }
        List<RoleEntity> roles = new ArrayList<>();

        for (UserEntity user : usersEntity) {
            roles.add(user.getRoleEntity());
        }

        return PrincipalUser.build(userEntity, roles);
    }
}
