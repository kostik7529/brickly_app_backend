package ru.brickly.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI bricklyOpenAPI() {
        Server productionServer = new Server();
        productionServer.setUrl("https://brickly-app.ru");
        productionServer.setDescription("Production server");

        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Local server");

        return new OpenAPI()
                .info(new Info().title("Brickly API").version("1.0"))
                .servers(List.of(productionServer, localServer)
                );
    }
}