package com.example.Java_Web.service;

import com.example.Java_Web.config.FeatureToggleConfig;
import org.springframework.stereotype.Service;

@Service
public class FeatureToggleService {

    private final FeatureToggleConfig featureToggleConfig;

    public FeatureToggleService(FeatureToggleConfig featureToggleConfig) {
        this.featureToggleConfig = featureToggleConfig;
    }

    public boolean isFeatureEnabled(String featureName) {
        return featureToggleConfig.check(featureName);
    }
}
