package com.sachin.URL_Shortener.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LinkAnalyticsResponse {
    private String longUrl;
    private String shortUrl;
    private LocalDateTime createdAt;
    private Long totalClicks;
}

