package com.rest.api.controllers;

import com.rest.api.models.Permission;
import com.rest.api.models.Role;
import com.rest.api.models.User;
import com.rest.api.repositories.PermissionRepository;
import com.rest.api.repositories.RoleRepository;
import com.rest.api.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SeedDataConfig implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;

    public SeedDataConfig(UserRepository userRepository, PasswordEncoder passwordEncoder, PermissionRepository permissionRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.permissionRepository = permissionRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0 && permissionRepository.count() == 0) {

//            Set<Permission> permissionsSet = new HashSet<>(permissionRepository.findAll());
//            Role role = roleRepository.findByRoleName("admin");
//            role.setPermissions(permissionsSet);
//
//            User admin = new User();
//            admin.setFirstName("admin");
//            admin.setFirstName("admin");
//            admin.setEmail("admin@admin.com");
//            admin.setPassword(passwordEncoder.encode("password"));
//            admin.setRoles((Set<Role>) role);
//            admin.isEnabled();
//            userRepository.save(admin);
//            LOGGER.debug("created ADMIN user - {}", admin);
        }

    }
}
