package com.example.devwebtalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DevwebTalkApp {

    public static void main(String[] args) {
        SpringApplication.run(DevwebTalkApp.class, args);
    }

}
