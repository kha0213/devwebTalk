package com.example.devwebtalk.repository.custom;

import com.example.devwebtalk.dto.UserModifyDto;
import com.example.devwebtalk.entity.QSocialLogin;
import com.example.devwebtalk.entity.QUser;
import com.example.devwebtalk.entity.SocialLogin;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.entity.type.SocialType;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.devwebtalk.entity.QSocialLogin.socialLogin;
import static com.example.devwebtalk.entity.QUser.*;
import static com.querydsl.core.group.GroupBy.*;

@Repository
@RequiredArgsConstructor
public class CustomUserRepositoryImpl implements CustomUserRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public UserModifyDto findUserModifyDtoByEmail(String email) {
        Map<User, Map<SocialType, SocialLogin>> transform = queryFactory.select(Projections.constructor(UserModifyDto.class,
                        user.email,
                        user.phone,
                        user.name,
                        user.birthday,
                        user.socials))
                .from(user)
                .leftJoin(user.socials, socialLogin)
                .where(user.email.eq(email))
                .transform(groupBy(user).as(map(socialLogin.socialType, socialLogin)));
        return transform.entrySet()
                .stream()
                .map(entry -> new UserModifyDto(entry.getKey(),entry.getValue()))
                .findAny()
                .orElseGet(UserModifyDto::new);
    }
}
