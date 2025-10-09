package com.careercrafter.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.*;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                    .title("CareerCrafter API")
                    .version("v1")
                    .description("CareerCrafter Job Portal - Backend APIs"));
    }
}
