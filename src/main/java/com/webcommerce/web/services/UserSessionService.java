package com.webcommerce.web.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserSessionService {
    <T extends  UserDetails> T getUser();
}
