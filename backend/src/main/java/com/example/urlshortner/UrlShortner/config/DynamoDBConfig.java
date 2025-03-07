package com.example.urlshortner.UrlShortner.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class DynamoDBConfig {

    @Bean
    public DynamoDbClient dynamoDbclient(){
        return DynamoDbClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }
}
