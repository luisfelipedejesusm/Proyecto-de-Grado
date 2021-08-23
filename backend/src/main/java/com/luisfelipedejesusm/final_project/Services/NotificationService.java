package com.luisfelipedejesusm.final_project.Services;

import com.luisfelipedejesusm.final_project.DTOs.Models.NotificationDTO;
import com.luisfelipedejesusm.final_project.Models.Notification;
import com.luisfelipedejesusm.final_project.Models.User;
import com.luisfelipedejesusm.final_project.Repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private SimpMessagingTemplate template;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(SimpMessagingTemplate simpMessagingTemplate){
        this.template = simpMessagingTemplate;
    }

    public void notify(User user, Notification notification){
        notification.setUser(user);
        notificationRepository.save(notification);
        this.template.convertAndSend("/topic/notification/" + user.getId(), new NotificationDTO(notification));
    }

    public void notify(List<User> users, Notification notification){
        users.forEach(user -> {
            notification.setUser(user);
            notificationRepository.save(notification);
            this.template.convertAndSend("/topic/notification/" + user.getId(), new NotificationDTO(notification));
        });
    }
}
