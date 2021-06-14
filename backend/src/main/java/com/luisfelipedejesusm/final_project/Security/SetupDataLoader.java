package com.luisfelipedejesusm.final_project.Security;

import com.luisfelipedejesusm.final_project.Models.ERole;
import com.luisfelipedejesusm.final_project.Models.Role;
import com.luisfelipedejesusm.final_project.Repositories.RoleRepository;
import com.luisfelipedejesusm.final_project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup)
            return;

        Arrays.stream(ERole.values()).forEach(role -> createRoleIfNotExists(role));

    }

    private void createRoleIfNotExists(ERole role) {
        Optional<Role> roleDB = roleRepository.findByName(role);
        if (roleDB.isEmpty()){
            Role newRole = new Role();
            newRole.setName(role);
            roleRepository.save(newRole);
        }
    }
}
