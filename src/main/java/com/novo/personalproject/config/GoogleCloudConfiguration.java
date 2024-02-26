package com.novo.personalproject.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class GoogleCloudConfiguration {

    @Value("${google-cloud.credentials-path}")
    private String credentialsPath;

    @Bean
    GoogleCredentials googleCredentials() throws IOException {
        return GoogleCredentials
                .fromStream(Files.newInputStream(Paths.get(credentialsPath)));
    }
    
    @Bean
    public Storage storage() throws IOException {
        return StorageOptions
                .newBuilder()
                .setCredentials(googleCredentials())
                .build()
                .getService();
    }

}
