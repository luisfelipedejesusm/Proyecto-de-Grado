package com.luisfelipedejesusm.final_project.Services;

import com.luisfelipedejesusm.final_project.DTOs.Models.CampaignDTO;
import com.luisfelipedejesusm.final_project.Models.Campaign;
import com.luisfelipedejesusm.final_project.Models.DonationCenter;
import com.luisfelipedejesusm.final_project.Repositories.CampaignRepository;
import com.luisfelipedejesusm.final_project.Repositories.DonationCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository repository;

    @Autowired
    private DonationCenterRepository donationCenterRepository;

    public void newCampaign(CampaignDTO request) {

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DonationCenter donationCenter = donationCenterRepository.findByUserId(userDetails.getId());


        Campaign campaign = new Campaign();
        campaign.setName(request.getName());
        campaign.setCampaignType(request.getCampaignType());
        campaign.setDescription(request.getDescription());
        campaign.setPhotoUrl(request.getPhotoUrl());
        campaign.setBloodType(request.getBloodType());
        campaign.setIsOpen(true);
        campaign.setTarget(request.getTarget());
        campaign.setExpiration(request.getExpiration());

        campaign.setDonationCenter(donationCenter);

        Campaign c2 = repository.save(campaign);

    }

    public List<Campaign> getCampaigns() {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        DonationCenter donationCenter = donationCenterRepository.findByUserId(userDetails.getId());

        return repository.findAllByDonationCenter(donationCenter);
    }

    public void activationToggle(Long id) {
        var campaign = repository.findById(id).orElse(null);
        if(campaign != null){
            campaign.setIsOpen(!campaign.getIsOpen());
            repository.save(campaign);
        }
    }
}
