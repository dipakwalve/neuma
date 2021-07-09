package com.duroflex.neuma.app.repository;

import com.duroflex.neuma.app.model.ErrorNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<ErrorNotification, Long> {
}
