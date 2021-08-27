package com.example.devwebtalk.setting.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-26
 * Time: 오후 2:21
 */
@Slf4j
class KISA_SEED_CBCTest {
    private final byte[] pbszUserKey = "TEST_JPA_STUDY!@".getBytes();
    private final byte[] pbszIV = "1234567890123456".getBytes();

    @Test
    @DisplayName("암복호화_테스트")
    public void encryptAndDecrypt_Test() {
        // given
        String rawMessage = "테스트 데이터";
        log.info("원본 데이터 => {}", rawMessage);
        // when
        byte[] encryptedMessage = KISA_SEED_CBC.SEED_CBC_Encrypt(pbszUserKey, pbszIV, rawMessage.getBytes(), 0, rawMessage.getBytes().length);
        log.info("암호화된 데이터 => " + encryptedMessage.toString());
        byte[] decryptedMessage = KISA_SEED_CBC.SEED_CBC_Decrypt(pbszUserKey, pbszIV, encryptedMessage, 0, encryptedMessage.length);
        log.info("복호화된 데이터 => " + new String(decryptedMessage));
        // then
        assertThat(rawMessage).isEqualTo(new String(decryptedMessage));
        assertThat(rawMessage).isNotEqualTo(encryptedMessage);
    }
}