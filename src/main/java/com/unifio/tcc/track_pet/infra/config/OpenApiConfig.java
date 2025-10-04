package com.unifio.tcc.track_pet.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gestão e Rastreamento de PETs API.")
                        .version("1.0")
                        .description("API para gestão e Rastreamento de PETs")
                        .termsOfService("")
                        .license(new License().name("").url("")));
    }
}
