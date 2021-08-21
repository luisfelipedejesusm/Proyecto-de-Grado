package com.luisfelipedejesusm.final_project.Models;

import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import com.luisfelipedejesusm.final_project.Enums.ECampaignType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "campaigns")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Campaign {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private ECampaignType campaignType;
    private String description;

    @Lob
    private String photoUrl;
    private EBloodType bloodType;
    private Boolean isOpen = true;

    @OneToMany(mappedBy = "campaign", fetch = FetchType.LAZY)
    private List<Donation> donations = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donation_center_id")
    private DonationCenter donationCenter;

    private Integer target;
    private LocalDate expiration;

    public Campaign(Long id){
        this.id = id;
    }
}
