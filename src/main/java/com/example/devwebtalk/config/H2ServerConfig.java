package com.example.devwebtalk.config;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-07-10
 * Time: 오후 3:42
 */

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;
@Profile("local")
@Configuration
public class H2ServerConfig {

    @Bean
    public Server h2DatabaseServer() throws SQLException {
        return Server.createTcpServer().start();
    }
}