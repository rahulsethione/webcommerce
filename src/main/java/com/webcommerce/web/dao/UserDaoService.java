package com.webcommerce.web.dao;

import com.webcommerce.web.entities.User;

import java.util.List;

public interface UserDaoService {
    User getUserByEmail(String email);
    User registerUser(User user);
    List<User> findAll();
}
