package ru.brickly.core.client.yookassa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Configuration
public class YookassaWebClientConfig {

    @Bean
    public WebClient yookassaWebClient(YookassaConfig config) {

        String auth = config.getShopId() + ":" + config.getSecretKey();

        String basicAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        return WebClient.builder()
                .baseUrl(config.getUrl())
                .defaultHeader(
                        HttpHeaders.AUTHORIZATION,
                        "Basic " + basicAuth)
                .build();
    }
}