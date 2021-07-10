package com.example.devwebtalk.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
public class User {
    @Id @GeneratedValue
    private Long id;

    private String name;

    public User(String name) {
        this.name = name;
    }


}
