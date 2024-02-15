package com.springbootlearning.springsecurity.event;

import com.springbootlearning.springsecurity.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

public class RegistrationCompleteEvent extends ApplicationEvent {

    @Getter
    private User user;

    @Getter
    private String applicationUrl;
    public RegistrationCompleteEvent(User user, String applicationUrl) {
        super(user);
        this.user = user;
        this.applicationUrl = applicationUrl;
    }

}
