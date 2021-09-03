package com.example.devwebtalk.entity;

import com.example.devwebtalk.entity.type.SocialType;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.*;
import static javax.persistence.GenerationType.*;

/**
 * 2021-07-26
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */

@ToString
@NoArgsConstructor
@Getter
@Setter
@TableGenerator(
        name = "USER_SOCIAL_SEQ_GENERATOR",
        table = "TB_SEQUENCES",
        pkColumnValue = "USER_SOCIAL_SEQ",
        allocationSize = 1) //TODO 운영엔 50으로 하자
@Entity
public class SocialLogin extends BaseEntity {
    @Id @GeneratedValue(strategy = TABLE)
    @Column(name = "USER_SOCIAL_ID")
    private Long id;

    @Enumerated(STRING)
    private SocialType socialType;

    @Column(name = "SOCIAL_EMAIL")
    private String email;

    @ToString.Exclude
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    //생성자
    public SocialLogin(SocialType socialType, String email) {
        this.socialType = socialType;
        this.email = email;
    }
}
