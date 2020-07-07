package com.webcommerce.web.services;

import com.webcommerce.web.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;

@Component
@SessionScope
public class UserSession {
    User user;

    @PostConstruct
    void postConstruct() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()) {
            user = (User)authentication.getPrincipal();
        }
    }

    public User getUser() {
        return user;
    }
}
