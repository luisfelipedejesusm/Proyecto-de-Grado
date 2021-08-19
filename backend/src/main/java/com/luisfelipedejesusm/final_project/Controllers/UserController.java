package com.luisfelipedejesusm.final_project.Controllers;

import com.luisfelipedejesusm.final_project.DTOs.Models.UserDTO;
import com.luisfelipedejesusm.final_project.DTOs.Requests.DonationCenterRequest;
import com.luisfelipedejesusm.final_project.DTOs.Responses.MessageResponse;
import com.luisfelipedejesusm.final_project.Models.User;
import com.luisfelipedejesusm.final_project.Repositories.UserRepository;
import com.luisfelipedejesusm.final_project.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

//@Secured({"ROLE_ADMIN", "ROLE_MODERATOR"})
@RestController
@RequestMapping("${endpoint}/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<UserDTO> getListOfUsers(){
        var users = userService.getListOfUsers();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @PatchMapping("/{id}")
    public MessageResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO request){
        userService.updateUser(id, request);
        return new MessageResponse("User Updated Correctly");
    }
}
