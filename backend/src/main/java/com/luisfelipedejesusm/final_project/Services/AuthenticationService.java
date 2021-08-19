package com.luisfelipedejesusm.final_project.Services;

import com.luisfelipedejesusm.final_project.DTOs.Models.UserDTO;
import com.luisfelipedejesusm.final_project.DTOs.Responses.JwtResponse;
import com.luisfelipedejesusm.final_project.DTOs.Requests.LoginRequest;
import com.luisfelipedejesusm.final_project.DTOs.Responses.MessageResponse;
import com.luisfelipedejesusm.final_project.DTOs.Requests.RegisterRequest;
import com.luisfelipedejesusm.final_project.Enums.EUserType;
import com.luisfelipedejesusm.final_project.Jwt.JwtUtils;
import com.luisfelipedejesusm.final_project.Enums.ERole;
import com.luisfelipedejesusm.final_project.Models.Role;
import com.luisfelipedejesusm.final_project.Models.User;
import com.luisfelipedejesusm.final_project.Repositories.RoleRepository;
import com.luisfelipedejesusm.final_project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                new UserDTO(userDetails.getUser())
        ));
    }

    public User registerUser(RegisterRequest registerData) {
        return registerUser(registerData, EUserType.USER);
    }

    public User registerUser(RegisterRequest registerData, EUserType userType) {
        if (userRepository.existsByUsername(registerData.getUsername())){
            throw new RuntimeException("User with username [" + registerData.getUsername() + "] already exists");
        }
        if (userRepository.existsByEmail(registerData.getEmail())){
            throw new RuntimeException("User with email [" + registerData.getEmail() + "] already exists");
        }
        // Create new user
        User user = new User();
        user.setUserType(userType);
        user.setFirstName(registerData.getName());
        user.setUsername(registerData.getUsername());
        user.setEmail(registerData.getEmail());
        user.setPassword(passwordEncoder.encode(registerData.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(
                roleRepository.findByName(ERole.ROLE_USER).orElseThrow(()->
                        new RuntimeException("Role not found"))
        );
        user.setRoles(roles);
        return userRepository.save(user);
    }
}
