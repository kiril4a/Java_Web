package com.example.Java_Web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "feature")
@Data
public class FeatureToggleConfig {
    private Map<String, Boolean> cosmoCats;
    private Map<String, Boolean> kittyProducts;
}
