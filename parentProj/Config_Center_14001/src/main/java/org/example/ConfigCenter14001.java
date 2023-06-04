package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigCenter14001 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenter14001.class,args);

    }
}
