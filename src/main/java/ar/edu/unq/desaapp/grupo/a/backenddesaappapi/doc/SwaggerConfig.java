package ar.edu.unq.desaapp.grupo.a.backenddesaappapi.doc;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi authApi(){
        String[] packagesToScan = {"ar.edu.unq.desaapp.grupo.a.backenddesaappapi.webservices"};

        return GroupedOpenApi.builder()
                .group("Authorization")
                .packagesToScan(packagesToScan)
                .build();
    }
}
