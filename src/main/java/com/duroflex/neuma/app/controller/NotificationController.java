package com.duroflex.neuma.app.controller;

import com.duroflex.neuma.app.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/notification")
    public ResponseEntity<?> getNotification(Integer code) {
        return ResponseEntity.ok(notificationService.getNotification(code));
    }

}
