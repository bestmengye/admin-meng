package com.meng.pc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication

public class PcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PcApplication.class, args);
    }

}
