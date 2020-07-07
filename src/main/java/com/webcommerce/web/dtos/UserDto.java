package com.webcommerce.web.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webcommerce.web.entities.User;

public class UserDto extends User {

    public UserDto(String id, String name, String email, String password, String phone) {
        super(id, name, email, password, phone);
    }

    public UserDto(User user) {
        super(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getPhone());
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isPersisted() {
        return super.isPersisted();
    }

    @Override
    @JsonIgnore
    public boolean isNew() {
        return super.isNew();
    }
}
