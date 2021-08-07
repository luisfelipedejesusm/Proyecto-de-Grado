package com.luisfelipedejesusm.final_project.Repositories;

import com.luisfelipedejesusm.final_project.DTOs.Models.DonationDTO;
import com.luisfelipedejesusm.final_project.Models.Donation;
import com.luisfelipedejesusm.final_project.Models.DonationCenter;
import com.luisfelipedejesusm.final_project.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findAllByDonationCenter(DonationCenter donationCenter);

    List<Donation> findAllByUser(User user);

    DonationDTO findFirstByUserOrderByIdAsc(User user);
}
