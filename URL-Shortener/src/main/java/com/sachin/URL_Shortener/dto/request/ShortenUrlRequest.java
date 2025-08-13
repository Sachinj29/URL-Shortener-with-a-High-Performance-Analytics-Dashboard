package com.sachin.URL_Shortener.dto.request;




import lombok.Data;
import org.hibernate.validator.constraints.URL;
import jakarta.validation.constraints.NotBlank;

@Data
public class ShortenUrlRequest {
    @NotBlank
    @URL(message = "Please provide a valid URL")
    private String longUrl;
}
