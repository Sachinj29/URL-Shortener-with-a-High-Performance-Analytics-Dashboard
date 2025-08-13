package com.sachin.URL_Shortener.service;



import com.sachin.URL_Shortener.model.entity.ClickEvent;
import com.sachin.URL_Shortener.model.entity.UrlMapping;
import com.sachin.URL_Shortener.repository.ClickEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class AnalyticsService {

    @Autowired
    private ClickEventRepository clickEventRepository;

    @Async // Makes this method run in a separate thread
    public void logClick(UrlMapping urlMapping, HttpServletRequest request) {
        ClickEvent clickEvent = ClickEvent.builder()
                .urlMapping(urlMapping)
                .ipAddress(request.getRemoteAddr())
                .userAgent(request.getHeader("User-Agent"))
                .referrer(request.getHeader("Referer"))
                .build();

        clickEventRepository.save(clickEvent);
    }
}

