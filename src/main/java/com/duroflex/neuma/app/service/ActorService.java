package com.duroflex.neuma.app.service;

import com.duroflex.neuma.app.model.ActorDetails;
import com.duroflex.neuma.app.model.UserSession;
import com.duroflex.neuma.app.repository.UserRepository;
import com.duroflex.neuma.app.repository.UserSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSessionRepository userSessionRepository;

    public Map<String, Object> findById(Integer userId) {

        Map<String, Object> userData = new HashMap<>();
        ActorDetails obj = userRepository.findByUserId(userId);

        userData.put("userId", obj.getFullName());
        userData.put("fullName", obj.getUserId());

        return userData;
    }

    public Map<String, Object> updateProfile(Integer userId, String fullName) {
        Map<String, Object> objUser = new HashMap<>();
        Integer i = userRepository.updateProfile(userId, fullName);
        if (i == 1) {
            ActorDetails actor = userRepository.findByUserId(userId);
            objUser.put("userId", actor.getUserId());
            objUser.put("fullName", actor.getFullName());
        } else {
            objUser.put("message", "Records Not found");
        }
        return objUser;
    }

    public ActorDetails saveNewUser(String fullName, String mobileNo) {

        ActorDetails actor = new ActorDetails();
        actor.setMobileNo(mobileNo);
        actor.setFullName(fullName);
        return userRepository.save(actor);

    }

    public Optional<ActorDetails> getLoginUserDetails(String mobileNumber) {
        Optional<ActorDetails> actor = userRepository.findByMobileNo(mobileNumber);
        if (actor.isPresent()) {
            UserSession session = new UserSession();

            session.setLastLogin(new Date());
            session.setUserId(actor.get().getUserId());
            session.setLoginFlag(true);
            userSessionRepository.save(session);
            return actor;
        } else {
            return null;
        }

    }

    public Integer getLoginUserDetails(Integer userId) {
        UserSession session = userSessionRepository.findByUserId(userId);
        Integer status = 0;
        if (session != null) {
            status = userSessionRepository.logoutSession(new Date(), session.getSessionId(), false);
        }
        return status;
    }

    public boolean modifyBootstrappingStatus(Integer userId) {
        Integer status = userRepository.updateBootstrappingStatus(true, userId);
        return status == 1;
    }

    public boolean getBootstrappingStatus(Integer userId) {
        boolean status = userRepository.getBootstrappingStatus(userId);
        return status;
    }
}

