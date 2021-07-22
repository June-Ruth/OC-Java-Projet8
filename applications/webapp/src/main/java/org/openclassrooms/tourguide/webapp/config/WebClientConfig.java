package org.openclassrooms.tourguide.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient getWebClientUserApi() {
        return WebClient.create("http://172.20.0.1:8081");
    }

    @Bean
    public WebClient getWebClientGpsApi() {
        return WebClient.create("http://172.20.0.2:8082");
    }

    @Bean
    public WebClient getWebClientRewardApi() {
        return WebClient.create("http://172.20.0.3:8083");
    }

    @Bean
    public WebClient getWebClientTripApi() {
        return WebClient.create("http://172.20.0.4:8084");
    }
}
