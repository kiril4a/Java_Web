package com.example.Java_Web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "feature")
public class FeatureToggleConfig {

    private final Map<String, Boolean> toggles = new HashMap<>();

    public Map<String, Boolean> getToggles() {
        return toggles;
    }

    public void setToggles(Map<String, Boolean> toggles) {
        this.toggles.clear();
        this.toggles.putAll(toggles);
    }

    public boolean isEnabled(String featureName) {
        return toggles.getOrDefault(featureName, false);
    }
}
