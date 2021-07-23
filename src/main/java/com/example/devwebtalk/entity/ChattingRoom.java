package com.example.devwebtalk.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.CascadeType.*;
import static javax.persistence.GenerationType.TABLE;

/**
 * 2021-07-21
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@TableGenerator(
        name = "CHATTING_ROOM_SEQ_GENERATOR",
        table = "TB_SEQUENCES",
        pkColumnValue = "ROOM_SEQ",
        allocationSize = 1) //TODO 운영엔 50으로 하자
public class ChattingRoom extends BaseEntity {
    @Id @GeneratedValue(strategy = TABLE, generator = "CHATTING_ROOM_SEQ_GENERATOR")
    @Column(name="CHATTING_ROOM_ID")
    private Long id;

    private String name;
    
    @ManyToMany
    private List<User> users = new ArrayList<>();

    @OneToMany(orphanRemoval = true, cascade = ALL)
    private List<Chat> chats = new ArrayList<>();

    public ChattingRoom(List<User> users) {
        this.users = users;
    }

    /**
     * [채팅방 이름 규칙]
     * 2인 : 이름
     * 3~5인 : 이름 나열  뒤 인원 수
     * 5인 이상 : 이름 5개 ... 뒤 인원 수
     */
    public void makeName() {
        //TODO 임시로 숫자 씀 나중 상수 처리
        int size = users.size();
        if(size < 2) {
            name = "";
        } else if (size == 2) {
            name = users.get(0).getName() + ", " + users.get(1).getName();
        } else if (size <= 5) {
            name = users.stream().map(User::getName).collect(Collectors.joining(", ")) + " (" + size + ")";
        } else {
            name = users.subList(0,5).stream().map(User::getName).collect(Collectors.joining(", ")) + " (" + size + ")";
        }
    }
}
