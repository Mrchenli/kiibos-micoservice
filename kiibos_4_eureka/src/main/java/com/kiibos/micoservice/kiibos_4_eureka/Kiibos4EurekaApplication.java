package com.kiibos.micoservice.kiibos_4_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Kiibos4EurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Kiibos4EurekaApplication.class, args);
    }

}
