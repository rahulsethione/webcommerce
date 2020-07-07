package com.webcommerce.web.dao;

import com.webcommerce.web.entities.User;
import com.webcommerce.web.repositories.UserRepository;
import org.apache.commons.collections4.map.LRUMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserDaoServiceImpl implements UserDaoService, UserDetailsService {

    private final LRUMap<String, User> userCacheStore = new LRUMap<String, User>(2000);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public User getUserByEmail(String email) {
        Query query = new Query(Criteria.where("email").is(email));

        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userCacheStore.get(email);

        if(Objects.isNull(user)) {
            user = getUserByEmail(email);

            if(Objects.nonNull(user)) {
                userCacheStore.put(email, user);
            }
        }

        return user;
    }
}
