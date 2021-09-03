package com.example.devwebtalk.repository;

import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.entity.UserLoginRemember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-27
 * Time: 오후 4:59
 */
public interface UserLoginRememberRepository extends JpaRepository<UserLoginRemember, Long> {
    @Query(value = "select u.user from UserLoginRemember as u where u.cookieValue = :cookieValue")
    Optional<User> findByCookieValue(@Param("cookieValue") String cookieValue);

    Optional<UserLoginRemember> findUserLoginRememberByCookieValue(@Param("cookieValue") String cookieValue);

    void deleteByCookieValue(@Param("cookieValue") String cookieValue);
}
