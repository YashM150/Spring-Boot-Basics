package com.matthe.ecom.config;

import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("E-Commerce API")
                        .version("1.0")
                        .description("API documentation for the E-Commerce system"));
    }

    @Bean
    public YAMLMapper yamlMapper() {
        return new YAMLMapper();
    }

}
