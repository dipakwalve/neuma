package com.duroflex.neuma.app.controller;

import com.duroflex.neuma.app.exception.exceptionutils.CustomException;
import com.duroflex.neuma.app.model.ActorDetails;
import com.duroflex.neuma.app.model.ActorRole;
import com.duroflex.neuma.app.model.ERole;
import com.duroflex.neuma.app.payload.request.LoginRequest;
import com.duroflex.neuma.app.payload.request.SignupRequest;
import com.duroflex.neuma.app.payload.response.AuthenticationResponse;
import com.duroflex.neuma.app.payload.response.MessageResponse;
import com.duroflex.neuma.app.payload.response.PlainMessageResponse;
import com.duroflex.neuma.app.repository.RoleRepository;
import com.duroflex.neuma.app.repository.UserRepository;
import com.duroflex.neuma.app.service.ActorService;
import com.duroflex.neuma.app.service.MyUserDetailsService;
import com.duroflex.neuma.app.util.jwt.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LoginController {

    Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private MyUserDetailsService userDetailsService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ActorService actorService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationManager(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getMobileNo(), "neuma"));
        } catch (BadCredentialsException e) {
            throw new CustomException(e.getMessage());
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getMobileNo());
        final String jwt;
        try {
            jwt = jwtTokenUtil.generateToken(userDetails);
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping("/checkMobileExists")
    public ResponseEntity<MessageResponse> checkMobileExists(String mobileNo) {
        boolean status = userRepository.existsByMobileNo(mobileNo);
        if (status) {
            return ResponseEntity.ok(new MessageResponse("Mobile number already exists, Please Sign In"));

        } else {
            return ResponseEntity.ok(new MessageResponse("Mobile Number not found, Please Sign Up"));
        }
    }

    @PostMapping("/checkEmailExists")
    public ResponseEntity<MessageResponse> checkEmailExists(String email) {
        boolean status = userRepository.existsByEmailId(email);
        if (status) {
            return ResponseEntity.ok()
                    .body(new MessageResponse("Email address already exists, Please enter another email address"));

        } else {
            return ResponseEntity.ok().body(new MessageResponse("Email not found, Please Sign up"));

        }
    }


    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        boolean status = userRepository.existsByMobileNo(signUpRequest.getMobileNo());
        log.info("Signup initiates");
        ActorDetails user = null;
        Set<ActorRole> roles = new HashSet<>();
        // Create new user's account
        String notFoundError = "Error: Role is not found.";
        if (!status) {
            user = new ActorDetails(signUpRequest.getMobileNo(), signUpRequest.getEmailId(), signUpRequest.getAddress(),
                    signUpRequest.getFullName(), true);

            Set<String> strRoles = signUpRequest.getRole();

            if (strRoles == null) {
                ActorRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException(""));
                roles.add(userRole);
            } else {
                log.trace("Role setting");
                strRoles.forEach(role -> {
                    switch (role) {
                        case "admin":
                            ActorRole adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                    .orElseThrow(() -> new RuntimeException(notFoundError));
                            roles.add(adminRole);

                            break;
                        case "mod":
                            ActorRole modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                    .orElseThrow(() -> new RuntimeException(notFoundError));
                            roles.add(modRole);

                            break;
                        default:
                            ActorRole userRole = roleRepository.findByName(ERole.ROLE_USER)
                                    .orElseThrow(() -> new RuntimeException(notFoundError));
                            roles.add(userRole);
                    }
                });
            }

            user.setRoles(roles);
            userRepository.save(user);
            return ResponseEntity.ok(new MessageResponse("User registered successfully!", user.getUserId()));

        } else {
            return ResponseEntity.ok(new MessageResponse("User Already Register "));

        }
    }

    @PostMapping("/login")
    public ResponseEntity<Optional<ActorDetails>> loginUserDetails(@RequestParam String mobileNo) {
        return ResponseEntity.ok(actorService.getLoginUserDetails(mobileNo));
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logoutUserDetails(@RequestParam Integer userId) {
        Integer status = actorService.getLoginUserDetails(userId);
        if (status > 0) {
            return ResponseEntity.ok(new MessageResponse("User Log out successfully"));

        } else {
            return ResponseEntity.ok(new MessageResponse("You Went something wrong"));

        }
    }

    @GetMapping("/getBootstrappingStatus")
    public ResponseEntity<Boolean> getBootstrappingStatus(@RequestParam Integer userId) {
        return ResponseEntity.ok(actorService.getBootstrappingStatus(userId));
    }

    @PutMapping("/updateBootstrappingStatus")
    public ResponseEntity<PlainMessageResponse> modifyBootstrappingStatus(@RequestParam Integer userId) {
        String message;
        if (actorService.modifyBootstrappingStatus(userId)){
            message="Bootstrapping Completed ";
        }else {
             message="Error Occurred";
        }
        return ResponseEntity.ok(new PlainMessageResponse(message));
    }
}
