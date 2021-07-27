package com.example.devwebtalk;

import com.example.devwebtalk.entity.QUser;
import com.example.devwebtalk.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@SpringBootTest
@Transactional
class DevwebTalkApplicationTests {

    @Autowired
    EntityManager em;

    // query dsl 작동 테스트
    @Test
    void contextLoads() {
        User user = new User();
        em.persist(user);

        JPAQueryFactory query = new JPAQueryFactory(em);
        QUser qUser = QUser.user;
        User result = query
                .selectFrom(qUser)
                .fetchOne();

        Assertions.assertThat(result).isEqualTo(user);
    }

}
