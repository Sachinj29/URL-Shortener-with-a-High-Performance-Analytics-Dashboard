package com.sachin.URL_Shortener.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShortUrlResponse {
    private String shortUrl;
    private String longUrl;
}
