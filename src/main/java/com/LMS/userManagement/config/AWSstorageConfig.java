/*
package com.LMS.userManagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
public class AWSstorageConfig {

    private String accessKey;

    private String accessSecret;

    @Value("${aws.region}")
    private String region;


public S3Client s3Client(){
    S3Client client= S3Client.builder()
            .region(Region.of(region))
            .build();

    return client;
}
}
*/
