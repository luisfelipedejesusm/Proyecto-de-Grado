package com.luisfelipedejesusm.final_project.Services;

import com.luisfelipedejesusm.final_project.DTOs.Models.CampaignDTO;
import com.luisfelipedejesusm.final_project.DTOs.Models.NotificationDTO;
import com.luisfelipedejesusm.final_project.Enums.EBloodType;
import com.luisfelipedejesusm.final_project.Enums.EUserType;
import com.luisfelipedejesusm.final_project.Models.Campaign;
import com.luisfelipedejesusm.final_project.Models.DonationCenter;
import com.luisfelipedejesusm.final_project.Models.Notification;
import com.luisfelipedejesusm.final_project.Models.User;
import com.luisfelipedejesusm.final_project.Repositories.CampaignRepository;
import com.luisfelipedejesusm.final_project.Repositories.DonationCenterRepository;
import com.luisfelipedejesusm.final_project.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository repository;

    @Autowired
    private DonationCenterRepository donationCenterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationService notificationService;

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
        var camp = repository.save(campaign);

        Notification notification = new Notification();
        notification.setTitle(request.getName());
        notification.setDescription("Nueva campaña de donacion creada por: " + donationCenter.getName());
        notification.setDateAndTime(LocalDateTime.now());
        notification.setCampaign(camp);

        List<User> users;
        if(request.getBloodType() != EBloodType.ALL){
            users = userRepository.findAllByBloodTypeAndUserType(request.getBloodType(), EUserType.USER);
        }else{
            users = userRepository.findAllByUserType(EUserType.USER);
        }
        notificationService.notify(users, notification);


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

    public List<Campaign> getLastCampaigns() {
        return repository.findTop3ByIsOpenOrderByIdDesc(true);
    }
}
