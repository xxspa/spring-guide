package com.newzhxu.application;

import com.newzhxu.bandwagon.BandWagone;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(BandWagone ctx) {
        return args -> {
            String start = ctx.start();
            System.out.println(start);
        };
    }
}
