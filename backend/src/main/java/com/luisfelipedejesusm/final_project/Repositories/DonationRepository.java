package com.luisfelipedejesusm.final_project.Repositories;

import com.luisfelipedejesusm.final_project.DTOs.Models.DonationDTO;
import com.luisfelipedejesusm.final_project.Models.Donation;
import com.luisfelipedejesusm.final_project.Models.DonationCenter;
import com.luisfelipedejesusm.final_project.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {
//    List<Donation> findAllByDonationCenter(DonationCenter donationCenter);

    DonationDTO findFirstByUserAndCancelledOrderByIdAsc(User user, boolean b);

    List<Donation> findAllByUserAndCancelled(User user, boolean b);

    List<Donation> findAllByDonationCenterAndCancelled(DonationCenter donationCenter, boolean b);
}
