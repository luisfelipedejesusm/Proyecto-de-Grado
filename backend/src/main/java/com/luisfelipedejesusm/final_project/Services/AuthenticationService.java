package com.luisfelipedejesusm.final_project.Services;

import com.luisfelipedejesusm.final_project.DTOs.JwtResponse;
import com.luisfelipedejesusm.final_project.DTOs.LoginRequest;
import com.luisfelipedejesusm.final_project.DTOs.MessageResponse;
import com.luisfelipedejesusm.final_project.DTOs.RegisterRequest;
import com.luisfelipedejesusm.final_project.Jwt.JwtUtils;
import com.luisfelipedejesusm.final_project.Models.ERole;
import com.luisfelipedejesusm.final_project.Models.Role;
import com.luisfelipedejesusm.final_project.Models.User;
import com.luisfelipedejesusm.final_project.Repositories.RoleRepository;
import com.luisfelipedejesusm.final_project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> authenticateUser(LoginRequest loginData) {
        // Create an Authentication Object using the data provided by the user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginData.getUsername(), loginData.getPassword())
        );
        // Login the user if found
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Create a Jwt Token
        String jwt = jwtUtils.generateJwtToken(authentication);
        // Get authenticated user roles
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles
        ));
    }

    public ResponseEntity<?> registerUser(RegisterRequest registerData) {
        if (userRepository.existsByUsername(registerData.getUsername())){
            return ResponseEntity.badRequest().body( new MessageResponse("Error: Username already exist") );
        }
        if (userRepository.existsByEmail(registerData.getEmail())){
            return ResponseEntity.badRequest().body( new MessageResponse("Error: Email already in use"));
        }
        // Create new user
        User user = new User();
        user.setName(registerData.getName());
        user.setUsername(registerData.getUsername());
        user.setEmail(registerData.getEmail());
        user.setPassword(passwordEncoder.encode(registerData.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(
                roleRepository.findByName(ERole.ROLE_USER).orElseThrow(()->
                        new RuntimeException("Role not found"))
        );
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User Registered Successfully"));
    }
}
