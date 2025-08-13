package com.sachin.URL_Shortener.model.util;


import org.springframework.stereotype.Component;

@Component
public class Base62Converter {

    private static final String BASE62_CHARS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int BASE = 62;

    public String fromBase10(long n) {
        if (n == 0) {
            return String.valueOf(BASE62_CHARS.charAt(0));
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(BASE62_CHARS.charAt((int) (n % BASE)));
            n /= BASE;
        }
        return sb.reverse().toString();
    }
}
