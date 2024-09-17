package com.mensal.project.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {


    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info ()
                                .title("EVENT SYSTEM")
                                .description("API PARA GERENCIADOR DE EVENTOS")
                                .version("v1")
                                .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))

                );
    }
}
