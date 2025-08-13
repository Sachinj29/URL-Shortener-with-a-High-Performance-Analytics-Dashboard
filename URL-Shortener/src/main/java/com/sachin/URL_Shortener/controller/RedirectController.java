package com.sachin.URL_Shortener.controller;


import com.sachin.URL_Shortener.model.entity.UrlMapping;
import com.sachin.URL_Shortener.repository.UrlMappingRepository;
import com.sachin.URL_Shortener.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.Optional;

@RestController
public class RedirectController {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode, HttpServletRequest request) {
        Optional<UrlMapping> urlMappingOptional = urlMappingRepository.findByShortCode(shortCode);

        if (urlMappingOptional.isPresent()) {
            UrlMapping urlMapping = urlMappingOptional.get();

            // Asynchronously log the click event
            analyticsService.logClick(urlMapping, request);

            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create(urlMapping.getLongUrl()))
                    .build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
