package com.springbootlearning.springsecurity.event.listener;

import com.springbootlearning.springsecurity.entity.User;
import com.springbootlearning.springsecurity.event.RegistrationCompleteEvent;
import com.springbootlearning.springsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent> {

    private final UserService userService;

    public RegistrationCompleteEventListener(UserService userService) {
        this.userService = userService;
    }
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        // Create Verification Token
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationTokenForUser(token, user);
        // Send mail to user
        String url = event.getApplicationUrl() + "/verifyRegistration?token="+token;
        log.info("Click the link to verify your account {}", url);
    }
}
