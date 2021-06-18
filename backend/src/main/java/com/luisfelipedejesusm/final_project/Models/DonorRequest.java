package com.luisfelipedejesusm.final_project.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donor_requests")
public class DonorRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EBloodType bloodType;

    private String comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donor_id", nullable = false)
    private User donor;

}
