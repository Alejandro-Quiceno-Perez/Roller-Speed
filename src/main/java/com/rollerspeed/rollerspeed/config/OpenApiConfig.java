package com.rollerspeed.rollerspeed.config;

import io.swagger.v3.oas.models.OpenAPI; // Importación de la clase OpenAPI de la especificación OpenAPI 3
import io.swagger.v3.oas.models.info.Info; // Importación de la clase Info utilizada para describir la API
import org.springframework.context.annotation.Bean; // Importación de la anotación @Bean para definir un bean de Spring
import org.springframework.context.annotation.Configuration; // Importación de la anotación @Configuration para definir una clase de configuración de Spring



@Configuration
public class OpenApiConfig {
     @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info() // Crea una nueva instancia de Info para describir la API
                        .title("api_rollerspeed") // Establece el título de la API
                        .version("1.0") // Establece la versión de la API
                        .description("API para gestionar una escuela de patinage")); // Establece una descripción de la API
    }
}
