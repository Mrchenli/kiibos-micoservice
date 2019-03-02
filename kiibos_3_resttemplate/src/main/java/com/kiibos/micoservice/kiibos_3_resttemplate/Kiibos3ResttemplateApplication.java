package com.kiibos.micoservice.kiibos_3_resttemplate;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableSwagger2Doc
public class Kiibos3ResttemplateApplication{

    @Autowired
    private RestTemplateBuilder builder;

    @Bean
    public RestTemplate restTemplate(){
        return builder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(Kiibos3ResttemplateApplication.class, args);
    }

}
