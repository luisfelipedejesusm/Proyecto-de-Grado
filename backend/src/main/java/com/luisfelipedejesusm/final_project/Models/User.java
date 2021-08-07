package com.luisfelipedejesusm.final_project.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import com.luisfelipedejesusm.final_project.Enums.EUserType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    private String lastName;
    private String phoneNumber;
    private String address;
    private EBloodType bloodType;

    private EUserType userType;

    private Boolean isDonor;

    private Double latitude;
    private Double longitude;

    // List of donors the user has submitted a request for blood
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<DonorRequest> donorRequests = new ArrayList<>();

    // List of request this user have been the donor
    @OneToMany(mappedBy = "donor", fetch = FetchType.LAZY)
    private List<DonorRequest> donationRequests = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(Long id){
        this.id = id;
    }

}
