package org.exp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class ConsumerApplication10001 {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication10001.class,args);

    }
}
