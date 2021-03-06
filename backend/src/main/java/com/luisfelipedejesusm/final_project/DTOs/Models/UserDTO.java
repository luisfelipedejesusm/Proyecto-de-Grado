package com.luisfelipedejesusm.final_project.DTOs.Models;

import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import com.luisfelipedejesusm.final_project.Enums.EUserType;
import com.luisfelipedejesusm.final_project.Models.Donation;
import com.luisfelipedejesusm.final_project.Models.Notification;
import com.luisfelipedejesusm.final_project.Models.Role;
import com.luisfelipedejesusm.final_project.Models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
//    private String name;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private EBloodType bloodType;

    private String username;
    private String email;
    private Boolean isDonor;
    private Set<Role> roles;

    private EUserType userType;

    private Double latitude;
    private Double longitude;

    private List<NotificationDTO> notifications;

    public UserDTO(Donation donation) {
        this.firstName = donation.getFirstName();
        this.lastName = donation.getLastName();
        this.phoneNumber = donation.getPhoneNumber();
        this.address = donation.getAddress();
        this.bloodType = donation.getBloodGroup();
        this.email = donation.getEmail();
    }

    public UserDTO(User user){
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.phoneNumber = user.getPhoneNumber();
        this.address = user.getAddress();
        this.bloodType = user.getBloodType();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.isDonor = user.getIsDonor();
        this.roles = user.getRoles();
        this.userType = user.getUserType();
        this.latitude = user.getLatitude();
        this.longitude = user.getLongitude();
//        this.roles = user.getRoles().stream().map(Role::toString).collect(Collectors.toSet());
        this.notifications = user.getNotifications().stream().map(NotificationDTO::new).collect(Collectors.toList());
    }
}
