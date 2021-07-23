package com.example.devwebtalk.entity;

import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-07-10
 * Time: 오후 3:29
 */
@ToString
@NoArgsConstructor
@Getter @Setter
@Table(name = "USERS")
@TableGenerator(
        name = "USER_SEQ_GENERATOR",
        table = "TB_SEQUENCES",
        pkColumnValue = "USER_SEQ",
        allocationSize = 1) //TODO 운영엔 50으로 하자
@Entity
public class User {
    @Id @GeneratedValue(strategy = TABLE, generator = "USER_SEQ_GENERATOR")
    @Column(name = "USER_ID")
    private Long id;

    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "users")
    private List<ChattingRoom> rooms = new ArrayList<>();

    /**
     * 생성자 함수
     */
    public User(String name) {
        this.name = name;
    }
}
