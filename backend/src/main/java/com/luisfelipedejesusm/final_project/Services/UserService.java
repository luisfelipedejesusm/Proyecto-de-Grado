package com.luisfelipedejesusm.final_project.Services;

import com.luisfelipedejesusm.final_project.DTOs.Models.UserDTO;
import com.luisfelipedejesusm.final_project.Models.User;
import com.luisfelipedejesusm.final_project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getListOfUsers() {
        return userRepository.findAll();
    }

    public void updateUser(Long id, UserDTO request) {
        var user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setIsDonor(request.getIsDonor());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setBloodType(request.getBloodType());
            user.setAddress(request.getAddress());
            user.setLatitude(request.getLatitude());
            user.setLongitude(request.getLongitude());

            userRepository.save(user);
        }
    }

    public List<User> getDonors() {
        return userRepository.findByIsDonor(true);
    }
}
