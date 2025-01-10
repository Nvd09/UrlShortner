package com.example.urlshortner.UrlShortner.repository;

import com.example.urlshortner.UrlShortner.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.endpoints.internal.Value;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.DynamoDbException;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UrlRepository {
//    public abstract UrlMapping findByShortUrl(String shortUrl);
//    public abstract UrlMapping findByOriginalUrl(String originalUrl);
//
    public final DynamoDbClient dynamoDbClient;

    public UrlRepository(DynamoDbClient dynamoDbClient){
        this.dynamoDbClient = dynamoDbClient;
    }
    // Method to save a new URL mapping into the Db
    public void save(String shortUrl, String originalUrl){
        //Create a map of attributes to represent the database row
        Map<String, AttributeValue> item = new HashMap<>();
        item.put("shortUrl", AttributeValue.builder().s(shortUrl).build());
        item.put("originalUrl", AttributeValue.builder().s(originalUrl).build());

        //Build the request to put the item into the DynamoDB table
        PutItemRequest request = PutItemRequest.builder()
                .tableName("url_mapping")
                .item(item)
                .build();
        //Use DynamoDb client to save the item
        dynamoDbClient.putItem(request);
    }
    public String findByShortUrl(String shortUrl){
        // Need to create a Key map to search for the specific record in the table
        Map<String,AttributeValue> key = new HashMap<>();
        //Partition the quuery for the table
        key.put("shortUrl", AttributeValue.builder().s(shortUrl).build());

        //Build the request to get the item by key
        GetItemRequest request = GetItemRequest.builder()
                .tableName("url_mapping")
                .key(key)
                .build();
        //Execute the request query to get response item from DynamoDB
        Map<String,AttributeValue> item = dynamoDbClient.getItem(request).item();

        //If item exists return the originalUrl; otherwise return Null
        return item != null ? item.get("originalUrl").s() : null;
    }
    public String findByOriginalUrl(String shortUrl) {
        // Build the key for DynamoDB query
        Map<String, AttributeValue> key = Map.of(
                "shortUrl", AttributeValue.builder().s(shortUrl).build()
        );

        // Create the GetItemRequest
        GetItemRequest request = GetItemRequest.builder()
                .tableName("UrlMapping")
                .key(key)
                .build();

        try {
            // Query DynamoDB
            Map<String, AttributeValue> returnedItem = dynamoDbClient.getItem(request).item();

            if (returnedItem != null && returnedItem.containsKey("originalUrl")) {
                return returnedItem.get("originalUrl").s(); // Return original URL
            } else {
                throw new RuntimeException("Short URL not found or missing original URL.");
            }
        } catch (DynamoDbException e) {
            throw new RuntimeException("DynamoDB query failed: " + e.getMessage(), e);
        }
    }


}
