package com.luisfelipedejesusm.final_project.Repositories;

import com.luisfelipedejesusm.final_project.DTOs.Models.CampaignDTO;
import com.luisfelipedejesusm.final_project.Models.Campaign;
import com.luisfelipedejesusm.final_project.Models.DonationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    List<Campaign> findAllByDonationCenter(DonationCenter donationCenter);


    List<Campaign> findTop3ByIsOpenOrderByIdDesc(boolean b);
}
