package org.openclassrooms.tourguide.trackerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient getWebClientUserApi() {
        return WebClient.create("http://localhost:8081");
    }

    @Bean
    public WebClient getWebClientGpsApi() {
        return WebClient.create("http://localhost:8082");
    }

    @Bean
    public WebClient getWebClientRewardApi() {
        return WebClient.create("http://localhost:8083");
    }

    @Bean
    public WebClient getWebClientTripApi() {
        return WebClient.create("http://localhost:8084");
    }
}
