package com.example.hibernatecoba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class HibernateCobaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HibernateCobaApplication.class, args);
    }

}
