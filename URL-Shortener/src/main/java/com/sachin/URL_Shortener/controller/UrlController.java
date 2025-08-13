package com.sachin.URL_Shortener.controller;



import com.sachin.URL_Shortener.dto.request.ShortenUrlRequest;
import com.sachin.URL_Shortener.dto.response.ShortUrlResponse;
import com.sachin.URL_Shortener.service.UrlShorteningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/links")
public class UrlController {

    @Autowired
    private UrlShorteningService urlShorteningService;

    @PostMapping
    public ResponseEntity<ShortUrlResponse> createShortUrl(
            @Valid @RequestBody ShortenUrlRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        ShortUrlResponse response = urlShorteningService.createShortUrl(request, userDetails.getUsername());
        return ResponseEntity.ok(response);
    }

    // TODO: Add endpoints for getting a user's links and their analytics
    // GET /api/links -> returns List<LinkAnalyticsResponse>
    // GET /api/links/{id}/analytics -> returns LinkAnalyticsResponse
}
