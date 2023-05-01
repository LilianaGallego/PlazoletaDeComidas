package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByIdAndRoleEntityId(Long idUser, Long idRole);
    Optional<UserEntity> findByDniNumber(String dniNumber);
    void deleteById(Long idUser);
    List<UserEntity> findAllByRoleEntityId(Long idRole, Pageable pageable);
    List<UserEntity> findAllById(Long idUser);
    Boolean existsByDniNumber(String dniNumber);

    boolean existsByMail(String mail);
}
