package org.openclassrooms.tourguide.userprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootApplication
public class UserProfile {

    public static void main(String[] args) {
        SpringApplication.run(UserProfile.class, args);
    }

}
