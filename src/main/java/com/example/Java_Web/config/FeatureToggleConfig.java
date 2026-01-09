package com.example.Java_Web.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties
public class FeatureToggleConfig {

    private Map<String, FeatureData> feature = new HashMap<>();

    public Map<String, FeatureData> getFeature() {
        return feature;
    }

    public void setFeature(Map<String, FeatureData> feature) {
        this.feature = feature;
    }
    
    public boolean check(String featureName) {
        FeatureData data = feature.get(featureName);
        return data != null && data.isEnabled();
    }
    
    public static class FeatureData {
        private boolean enabled;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }
    }
}
