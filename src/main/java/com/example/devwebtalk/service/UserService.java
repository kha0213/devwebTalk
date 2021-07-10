package com.example.devwebtalk.service;

import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-07-10
 * Time: 오후 3:31
 */
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public Long userAdd(User user) {
        User u = userRepository.save(user);
        return u.getId();
    }
}
