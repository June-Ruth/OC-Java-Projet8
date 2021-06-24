package org.openclassrooms.tourguide.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient getWebClientAttraction() {
        return WebClient.create("http://localhost:8081");
    }

    //TODO : configure WebClient Beans
}
