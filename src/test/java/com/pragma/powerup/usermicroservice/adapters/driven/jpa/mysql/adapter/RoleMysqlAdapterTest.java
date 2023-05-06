package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRoleEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoleMysqlAdapterTest {
    @Mock
    private IRoleRepository roleRepository;
    @Mock
    private IRoleEntityMapper roleEntityMapper;

    @InjectMocks
    private RoleMysqlAdapter roleMysqlAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldGetAllRolesSuccessfully() {
        // Arrange
        RoleEntity roleEntity1 = new RoleEntity();
        roleEntity1.setId(1L);
        roleEntity1.setName("ADMIN");
        roleEntity1.setDescription("ADMIN");

        RoleEntity roleEntity2 = new RoleEntity();
        roleEntity2.setId(2L);
        roleEntity2.setName("OWNER");
        roleEntity2.setDescription("OWNER");

        List<RoleEntity> roleEntityList = Arrays.asList(roleEntity1, roleEntity2);

        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ADMIN");
        role1.setDescription("ADMIN");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("OWNER");
        role2.setDescription("OWNER");

        List<Role> expectedRoleList = Arrays.asList(role1, role2);

        Mockito.when(roleRepository.findAll()).thenReturn(roleEntityList);
        Mockito.when(roleEntityMapper.toRoleList(roleEntityList)).thenReturn(expectedRoleList);

        // Act
        List<Role> actualRoleList = roleMysqlAdapter.getAllRoles();

        // Assert
        assertEquals(expectedRoleList, actualRoleList);
    }

    @Test
    public void shouldThrowNoDataFoundExceptionWhenNoRolesAreFound() {
        // Arrange
        Mockito.when(roleRepository.findAll()).thenReturn(Arrays.asList());
        Mockito.when(roleEntityMapper.toRoleList(Arrays.asList())).thenReturn(Arrays.asList());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> roleMysqlAdapter.getAllRoles());
    }

}