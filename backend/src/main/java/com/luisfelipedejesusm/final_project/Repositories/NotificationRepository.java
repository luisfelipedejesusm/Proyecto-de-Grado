package com.luisfelipedejesusm.final_project.Repositories;

import com.luisfelipedejesusm.final_project.Models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
