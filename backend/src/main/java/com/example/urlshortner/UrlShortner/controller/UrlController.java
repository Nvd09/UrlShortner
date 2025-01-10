package com.example.urlshortner.UrlShortner.controller;

import com.example.urlshortner.UrlShortner.model.UrlMapping;
import com.example.urlshortner.UrlShortner.service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/api/url")
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService = urlService;

    }

    //URL Controller should take care of two things->
    // Post Request - shorten the url
    // Get Request - give the original url
    @PostMapping("/shorten")
    @ResponseStatus(HttpStatus.CREATED)
    public UrlMapping shortenUrl(@RequestBody Map<String, String> request ){
        String originalUrl = request.get("originalUrl");
        return urlService.getShortenUrl(originalUrl);
    }

}
