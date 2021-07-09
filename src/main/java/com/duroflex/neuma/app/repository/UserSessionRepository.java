package com.duroflex.neuma.app.repository;

import com.duroflex.neuma.app.model.UserSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

    UserSession findByUserId(Integer userId);

    @Modifying
    @Transactional
    Integer logoutSession(Date logoutTime, Integer sessionId,boolean loginFlag);
}
