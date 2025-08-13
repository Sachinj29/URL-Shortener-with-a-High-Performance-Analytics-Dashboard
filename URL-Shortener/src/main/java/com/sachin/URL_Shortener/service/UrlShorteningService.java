package com.sachin.URL_Shortener.service;





import com.sachin.URL_Shortener.dto.request.ShortenUrlRequest;
import com.sachin.URL_Shortener.dto.response.ShortUrlResponse;
import com.sachin.URL_Shortener.model.entity.UrlMapping;
import com.sachin.URL_Shortener.model.entity.User;
import com.sachin.URL_Shortener.repository.UrlMappingRepository;
import com.sachin.URL_Shortener.repository.UserRepository;
import com.sachin.URL_Shortener.model.util.Base62Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UrlShorteningService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Base62Converter base62Converter;

    @Transactional
    public ShortUrlResponse createShortUrl(ShortenUrlRequest request, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Step 1: Save the entity to generate an ID
        UrlMapping urlMapping = UrlMapping.builder()
                .longUrl(request.getLongUrl())
                .user(user)
                .shortCode("") // Temporary value
                .build();
        UrlMapping savedMapping = urlMappingRepository.save(urlMapping);

        // Step 2: Convert the ID to base62 and update the entity
        String shortCode = base62Converter.fromBase10(savedMapping.getId());
        savedMapping.setShortCode(shortCode);
        urlMappingRepository.save(savedMapping);

        // This should be configurable based on your domain
        String fullShortUrl = "http://your.domain/" + shortCode;

        return new ShortUrlResponse(fullShortUrl, request.getLongUrl());
    }
}

