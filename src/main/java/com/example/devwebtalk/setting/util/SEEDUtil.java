package com.example.devwebtalk.setting.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-08-26
 * Time: 오후 2:05
 */
public class SEEDUtil {
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    //TODO 감출 방법 확인
    private static final byte[] pbszUserKey = "TEST_JPA_STUDY!@".getBytes();
    private static final byte[] pbszIV = "1234567890123456".getBytes();

    public static String encrypt(String rawMessage) {
        if(rawMessage == null) return null;
        byte[] message = rawMessage.getBytes(UTF_8);
        byte[] encryptedMessage = KISA_SEED_CBC.SEED_CBC_Encrypt(pbszUserKey, pbszIV, message, 0, message.length);
        return new String(Base64.getEncoder().encode(encryptedMessage), UTF_8);
    }
    public static String decrypt(String encryptedMessage) {
        if(encryptedMessage == null) return null;
        byte[] message = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedMessage = KISA_SEED_CBC.SEED_CBC_Decrypt(pbszUserKey, pbszIV, message, 0, message.length);
        return new String(decryptedMessage, UTF_8);
    }
}
