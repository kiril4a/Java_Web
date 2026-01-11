package com.example.Java_Web.service;

import com.example.Java_Web.config.FeatureToggleConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureToggleService {
    private final FeatureToggleConfig featureToggleConfig;

    public boolean isFeatureEnabled(String featureName) {
        if (featureName.equals("cosmoCats.enabled")) {
            return featureToggleConfig.getCosmoCats().getOrDefault("enabled", false);
        } else if (featureName.equals("kittyProducts.enabled")) {
            return featureToggleConfig.getKittyProducts().getOrDefault("enabled", false);
        }
        return false;
    }
}
