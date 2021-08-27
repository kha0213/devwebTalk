package com.example.devwebtalk.setting.util;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-26
 * Time: 오후 2:28
 */
@Slf4j
class SEEDUtilTest {
    @Test
    @DisplayName("암호화 테스트")
    void encrypt() {
        //given
        String rawMessage = "테스트메세지";
        //when
        final String encrypt = SEEDUtil.encrypt(rawMessage);
        log.info("[암호화] {} => {}",rawMessage, encrypt);
        //then
        assertThat(rawMessage).isNotEqualTo(encrypt);
    }

    @Test
    @DisplayName("복호화 테스트")
    void decrypt() {
        //given
        String rawMessage = "테스트메세지";
        //when
        final String encrypt = SEEDUtil.encrypt(rawMessage);
        log.info("[암호화] {} => {}",rawMessage, encrypt);
        final String decrypt = SEEDUtil.decrypt(encrypt);
        log.info("[복호화] {} => {}",encrypt, decrypt);
        //then
        assertThat(rawMessage).isEqualTo(decrypt);
    }
}