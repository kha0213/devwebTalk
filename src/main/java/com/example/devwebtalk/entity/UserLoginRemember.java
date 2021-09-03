package com.example.devwebtalk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.TABLE;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-27
 * Time: 오후 4:52
 */
@NoArgsConstructor
@Data
@TableGenerator(
        name = "LOGIN_REMEMBER_SEQ_GENERATOR",
        table = "TB_SEQUENCES",
        pkColumnValue = "USER_LOGIN_REMEMBER_ID",
        allocationSize = 1) //TODO 운영엔 50으로 하자
@Entity
public class UserLoginRemember extends BaseEntity {
    @Id @GeneratedValue(strategy = TABLE, generator = "LOGIN_REMEMBER_SEQ_GENERATOR")
    @Column(name = "USER_LOGIN_REMEMBER_ID")
    private Long id;

    private String cookieValue;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @Transient
    private boolean isNew;

    public UserLoginRemember(String cookieValue, User user) {
        this.cookieValue = cookieValue;
        this.user = user;
        this.isNew = true;
    }
}
