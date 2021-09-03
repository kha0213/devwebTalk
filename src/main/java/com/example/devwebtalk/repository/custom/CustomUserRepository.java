package com.example.devwebtalk.repository.custom;

import com.example.devwebtalk.dto.UserModifyDto;

public interface CustomUserRepository {
    public UserModifyDto findUserModifyDtoByEmail(String email);
}
