package com.duroflex.neuma.app.service;

import com.duroflex.neuma.app.model.ActorDetails;
import com.duroflex.neuma.app.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserDetailsService.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<ActorDetails> actor = null;
        String message = null;
        try {
            actor = userRepository.findByMobileNo(username);
            if (!(actor.isPresent())) {
                message = "Username not found";
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return new User(actor.get().getMobileNo(), "neuma", new ArrayList());
    }
}
