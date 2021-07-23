package com.example.devwebtalk.entity;

import com.example.devwebtalk.entity.type.ChatType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.*;

/**
 * 2021-07-12
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@NoArgsConstructor
@Getter @Setter
@TableGenerator(
        name = "CHAT_SEQ_GENERATOR",
        table = "TB_SEQUENCES",
        pkColumnValue = "CHAT_SEQ",
        allocationSize = 1) //TODO 운영엔 50으로 하자
@Entity
public class Chat extends BaseEntity {
    @Id
    @GeneratedValue(strategy = TABLE, generator = "CHAT_SEQ_GENERATOR")
    @Column(name = "CHAT_ID", updatable = false)
    private Long id;

    private String content;

    @Enumerated
    private ChatType type;

    @ManyToOne(fetch = LAZY)
    private User user;

    @ManyToOne(fetch = LAZY)
    private ChattingRoom room;

    /**
     * 생성자 함수
     */
    public Chat(String content, User user, ChattingRoom room) {
        this.type = ChatType.MESSAGE;
        this.content = content;
        this.user = user;
        this.room = room;
    }
}
