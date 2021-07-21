package org.openclassrooms.tourguide.trackerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;


@Configuration
public class WebClientConfig {

    private final HttpClient httpClient = HttpClient.create(ConnectionProvider.builder("fixed").pendingAcquireMaxCount(-1).build());

    @Bean
    public WebClient getWebClientUserApi() {
        return WebClient.builder()
                .baseUrl("http://172.20.0.1:8081")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean
    public WebClient getWebClientGpsApi() {
        return WebClient.builder()
                .baseUrl("http://172.20.0.2:8082")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    @Bean
    public WebClient getWebClientRewardApi() {
        return WebClient.builder()
                .baseUrl("http://172.20.0.3:8083")
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }
}
