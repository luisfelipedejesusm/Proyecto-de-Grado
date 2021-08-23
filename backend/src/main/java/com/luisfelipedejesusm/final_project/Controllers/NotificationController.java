package com.luisfelipedejesusm.final_project.Controllers;

import com.luisfelipedejesusm.final_project.DTOs.Models.NotificationDTO;
import com.luisfelipedejesusm.final_project.Enums.EUserType;
import com.luisfelipedejesusm.final_project.Models.Notification;
import com.luisfelipedejesusm.final_project.Models.User;
import com.luisfelipedejesusm.final_project.Repositories.UserRepository;
import com.luisfelipedejesusm.final_project.Services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("${endpoint}/public")
public class NotificationController {

@Autowired
private NotificationService service;

@Autowired
private UserRepository repository;

    @GetMapping("/notify")
//    @MessageMapping("/notify")
//    @SendTo("/topic/notification")
    public void getNotification() {
        var notification = new Notification();
        notification.setTitle("nueva notificacion");
        notification.setDescription("Lorem Ipsum DOlor Amet");

        service.notify(repository.findAllByUserType(EUserType.USER), notification);
//        this.template.convertAndSend("/topic/notification/1", notification);
//        return notification;
    }
}
