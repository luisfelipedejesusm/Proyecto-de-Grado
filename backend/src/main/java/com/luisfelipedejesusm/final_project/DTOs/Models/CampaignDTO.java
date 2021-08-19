package com.luisfelipedejesusm.final_project.DTOs.Models;

import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import com.luisfelipedejesusm.final_project.Enums.ECampaignType;
import com.luisfelipedejesusm.final_project.Models.Campaign;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CampaignDTO {
    private Long id;
    private String name;
    private ECampaignType campaignType;
    private String description;
    private String photoUrl;
    private EBloodType bloodType;
    private Boolean isOpen;
    private Integer target;
    private LocalDate expiration;
    private Long donationsReceived;

    public CampaignDTO(Campaign campaign){
        this.id = campaign.getId();
        this.name = campaign.getName();
        this.campaignType = campaign.getCampaignType();
        this.description = campaign.getDescription();
        this.photoUrl = campaign.getPhotoUrl();
        this.bloodType = campaign.getBloodType();
        this.isOpen = campaign.getIsOpen();
        this.target = campaign.getTarget();
        this.expiration = campaign.getExpiration();
        this.donationsReceived = campaign.getDonations().stream().count();
    }
}
