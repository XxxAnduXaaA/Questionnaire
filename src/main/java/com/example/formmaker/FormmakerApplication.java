package com.example.formmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FormmakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormmakerApplication.class, args);
    }
}
