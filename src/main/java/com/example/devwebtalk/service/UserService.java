package com.example.devwebtalk.service;

import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Long save(User user) {
        User u = userRepository.save(user);
        return u.getId();
    }

    /**
     * 유저 찾기
     * @param user
     * @return
     */
    public Optional<User> findById(User user) {
        return findById(user.getId());
    }
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
