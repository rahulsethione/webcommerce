package com.webcommerce.web.controllers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.webcommerce.web.dao.UserDaoService;
import com.webcommerce.web.dtos.UserDto;
import com.webcommerce.web.entities.User;
import com.webcommerce.web.services.UserSessionService;
import com.webcommerce.web.utils.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserSessionService userSessionService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    private static class LoginRequest {
        private final String email;
        private final String password;

        @JsonCreator
        public LoginRequest(@JsonProperty String email, @JsonProperty String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

    @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> signup(@RequestBody User user) {
        User existingUserByEmail = userDaoService.getUserByEmail(user.getEmail());

        if(Objects.isNull(existingUserByEmail)) {
            User newUser = userDaoService.registerUser(user);
            String token = jwtUtil.generateToken(new HashMap<>(), newUser);
            Map<String, Object> response = new HashMap<>();

            response.put("user", new UserDto(newUser));
            response.put("token", token);

            return response;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User with email" + "\"" + user.getEmail() + "\"" + " is already registered.");
        }
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> login(@RequestBody LoginRequest loginRequest) {
        User existingUserByEmail = userDaoService.getUserByEmail(loginRequest.getEmail());

        if(Objects.isNull(existingUserByEmail) || !existingUserByEmail.getPassword().equals(loginRequest.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email and password does no match.");
        } else {
            String token = jwtUtil.generateToken(new HashMap<>(), existingUserByEmail);
            Map<String, Object> response = new HashMap<>();

            response.put("user", new UserDto(existingUserByEmail));
            response.put("token", token);

            return response;
        }
    }

    @GetMapping(path = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsersList() {
        logger.info(userSessionService.getUser().toString());
        return userDaoService.findAll();
    }

//    @GetMapping(path = "/login/oauth/{provider}")
//    public String oauthLogin(@PathVariable final String provider) {
//
//    }

}
