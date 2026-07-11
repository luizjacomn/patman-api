package com.luizjacomn.patmanapi.shared.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Bean
    OpenAPI patientApi() {
        return new OpenAPI()
            .info(new Info()
                .title("Patman API")
                .description("API REST para gerenciamento de pacientes")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Luiz Jacó")
                    .email("luizjaco.dev@gmail.com")
                )
            )
            .externalDocs(new ExternalDocumentation()
                .description("Repositório do GitHub")
                .url("https://github.com/luizjacomn/patman-api")
            );
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
            .group("v1")
            .pathsToMatch("/v1/**")
            .build();
    }

}
