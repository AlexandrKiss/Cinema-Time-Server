package com.herokuapp.cinematime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CinemaTime {
    public static void main(String[] args) {
        SpringApplication.run(CinemaTime.class, args);
    }
}
