package com.example.devwebtalk.dto;

import com.example.devwebtalk.entity.SocialLogin;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.entity.type.SocialType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserModifyDto {
    private String email;

    private String phone;

    private String name;

    private LocalDateTime birthday;

    private Map<SocialType, SocialLogin> socials = new HashMap<>();

    public UserModifyDto(User user, Map<SocialType, SocialLogin> socials) {
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.name = user.getName();
        this.birthday = user.getBirthday();
        this.socials = socials;
    }
}
