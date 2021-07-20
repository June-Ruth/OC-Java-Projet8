package org.openclassrooms.tourguide.trackerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.netty.resources.ConnectionProvider;

@SpringBootApplication
public class TrackerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrackerApiApplication.class, args);


    }
}
