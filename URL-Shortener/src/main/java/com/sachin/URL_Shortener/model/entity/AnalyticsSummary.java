package com.sachin.URL_Shortener.model.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "analytics_summaries")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalyticsSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "url_mapping_id", nullable = false)
    private UrlMapping urlMapping;

    @Column(nullable = false)
    private Long totalClicks;

    // In a real system, these would be more structured, maybe in separate tables or using JSONB type
    private String topReferrers; // Simple example: comma-separated
    private String clicksByCountry; // Simple example: comma-separated
}

