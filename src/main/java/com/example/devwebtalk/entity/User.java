package com.example.devwebtalk.entity;

import com.example.devwebtalk.entity.type.SocialType;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.persistence.EnumType.*;
import static javax.persistence.GenerationType.*;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-07-10
 * Time: 오후 3:29
 */

@ToString
@NoArgsConstructor
@Getter
@Table(name = "USERS")
@TableGenerator(
        name = "USER_SEQ_GENERATOR",
        table = "TB_SEQUENCES",
        pkColumnValue = "USER_SEQ",
        allocationSize = 1) //TODO 운영엔 50으로 하자
@Entity
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = TABLE, generator = "USER_SEQ_GENERATOR")
    @Column(name = "USER_ID")
    private Long id;

    private String phone;

    private String email;

    private String name;

    private LocalDateTime birthday;

    private String pw;

    @OneToMany(mappedBy = "user")
    @MapKeyEnumerated(value = STRING)
    @ToString.Exclude
    private Map<SocialType, SocialLogin> socials = new HashMap<>();

    @ToString.Exclude
    @ManyToMany(mappedBy = "users")
    private List<ChattingRoom> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<FriendsGroup> friendsGroups = new ArrayList<>();

    /**
     * 생성자 함수
     */
    public User(String name) {
        this(name, null);
    }

    public User(String name, LocalDateTime birthday) {
        this(name, null, birthday);
    }

    public User(String name, String email, LocalDateTime localDateTime) {
        this(name, null, email, localDateTime);
    }

    public User(String name, String phone, String email, LocalDateTime localDateTime) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
    }

    public User(String name, String phone, String email, LocalDateTime birthday, String pw) {
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.pw = pw;
    }
}
