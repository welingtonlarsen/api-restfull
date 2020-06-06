package com.api.service.service.impl;

import com.api.entity.User;
import com.api.repository.UserRepository;
import com.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository repository;


    @Override
    public User save(User u) {
        return this.repository.save(u);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.repository.findByEmailEquals(email);
    }
}
