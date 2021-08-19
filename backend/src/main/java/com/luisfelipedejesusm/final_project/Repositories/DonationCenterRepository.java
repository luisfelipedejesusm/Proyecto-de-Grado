package com.luisfelipedejesusm.final_project.Repositories;

import com.luisfelipedejesusm.final_project.Models.DonationCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationCenterRepository extends JpaRepository<DonationCenter, Long> {
    DonationCenter findByUserId(Long id);
}
