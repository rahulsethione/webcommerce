package com.webcommerce.web.services;

import com.webcommerce.web.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class UserSessionServiceImpl implements UserSessionService {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public User getUser() {
        return applicationContext.getBean(UserSession.class).getUser();
    }
}
