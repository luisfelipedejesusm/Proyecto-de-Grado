package com.luisfelipedejesusm.final_project.DTOs.Models;

import com.luisfelipedejesusm.final_project.Models.Notification;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@AllArgsConstructor
@Data
public class NotificationDTO {
    private String title;
    private String desc;
    private LocalDateTime dateAndTime;
//    private Long campaignId;
    private CampaignDTO campaign;

    public NotificationDTO(Notification notification){
        this.title = notification.getTitle();
        this.desc = notification.getDescription();
        this.dateAndTime = notification.getDateAndTime();
        if(notification.getCampaign() != null)
            this.campaign = new CampaignDTO(notification.getCampaign());
    }
}
