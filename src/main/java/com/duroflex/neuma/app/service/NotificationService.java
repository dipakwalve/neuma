package com.duroflex.neuma.app.service;

import com.duroflex.neuma.app.model.ErrorNotification;
import com.duroflex.neuma.app.repository.NotificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
//get the notification message
@Service
public class NotificationService {

    Logger logger = LoggerFactory.getLogger(NotificationService.class);
    @Autowired
    private NotificationRepository notificationRepository;

    public Map<Integer, String> getNotification(Integer code) {
        List<ErrorNotification> listNotification = notificationRepository.findAll();
        Map<Integer, String> notification = new HashMap<>();
        Map<Integer, String> collect = null;
        try {
            Iterator itr = listNotification.iterator();
            while (itr.hasNext()) {
                ErrorNotification getNotification = (ErrorNotification) itr.next();
                notification.put(getNotification.getNotificationCode(), getNotification.getNotificationMessage());
            }
            collect = notification.entrySet().stream().filter(
                    map -> map.getKey() == code).collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue()));
            logger.info(String.valueOf(collect));
        } catch (Exception exception) {

            logger.error(exception.getMessage());
        }
        return collect;
    }
}
