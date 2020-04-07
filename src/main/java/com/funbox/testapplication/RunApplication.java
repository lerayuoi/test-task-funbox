package com.funbox.testapplication;

import com.funbox.testapplication.config.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunApplication {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{RunApplication.class, RedisConfig.class}, args);
    }

}
