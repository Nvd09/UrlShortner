package com.example.urlshortner.UrlShortner.service;

import com.example.urlshortner.UrlShortner.model.UrlMapping;
import com.example.urlshortner.UrlShortner.repository.UrlRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository){
        this.urlRepository = urlRepository;
    }

    public UrlMapping getShortenUrl(String originalUrl){
        //Here we want to generate the shortned URL
        // Then we can go ahead and create a new mapping
        // object. here we can set the orig and short
        // to the mapping object.
        String shortUrl = UUID.randomUUID().toString().substring(0,6);
        String existingUrl = urlRepository.findByShortUrl(originalUrl);
        if(existingUrl != null){
            // If a mapping already exists, We can return the original mapping
            return new UrlMapping(existingUrl, originalUrl);
        }
        // If not mapping exists, save the new short URL with the original url
        urlRepository.save(shortUrl, originalUrl);
        return new UrlMapping(shortUrl, originalUrl);
    }
    public String getOriginalUrl(String shortUrl) {
        // Retrieve the original URL from the repository.
        String originalUrl = urlRepository.findByOriginalUrl(shortUrl);

        // If no mapping is found, throw an exception.
        if (originalUrl == null) {
            throw new RuntimeException("No mapping found for the given short URL: " + shortUrl);
        }
        // Return the retrieved original URL.
        return originalUrl;
    }
}
