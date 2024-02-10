package com.novo.personalproject.config;

import com.backblaze.b2.client.B2StorageClient;
import com.backblaze.b2.client.B2StorageClientFactory;
import com.backblaze.b2.client.exceptions.B2Exception;
import com.backblaze.b2.client.structures.B2Bucket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackBlazeConfiguration {
    @Value("${back-blaze.b2.applicationKeyId}")
    private String keyId;

    @Value("${back-blaze.b2.applicationKey}")
    private String applicationKey;

    @Value("{back-blaze.b2.bucketName}")
    private String bucketName;

    @Value("{back-blaze.b2.userAgent}")
    private String userAgent;

    @Bean
    public B2StorageClient b2StorageClient(){

        return B2StorageClientFactory.createDefaultFactory().create(keyId, applicationKey, userAgent);
    }

    @Bean
    public B2Bucket b2Bucket(B2StorageClient b2StorageClient) throws B2Exception {
        return b2StorageClient.getBucketOrNullByName(bucketName);
    }
}
