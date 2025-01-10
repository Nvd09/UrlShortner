package com.example.urlshortner.UrlShortner.controller;

import com.example.urlshortner.UrlShortner.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URI;

@RestController
public class RedirectController {

    private final UrlService urlService;

    public RedirectController(UrlService urlService) {
        this.urlService = urlService;
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirectToOriginal(@PathVariable String shortUrl){
        String originalUrl = urlService.getOriginalUrl(shortUrl);
        if(originalUrl == null){
            return ResponseEntity.notFound().build();
        }
        if (!isValidUrl(originalUrl)) {
            return ResponseEntity.badRequest().build(); // 400 Bad Request for invalid URLs
        }
        System.out.println("Redirecting to: " + originalUrl);
        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(originalUrl))
                .build();
    }
    private boolean isValidUrl(String url) {
        try {
            new java.net.URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

}
