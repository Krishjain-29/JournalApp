package com.practice.journalApp.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi(){
        return new OpenAPI().info(new Info().title("JOURNAL APP")
                .description("Basic Journal Application")
                .contact(new Contact().name("Krish Jain").email("krishjain2902@gmail.com")));
    }
}
