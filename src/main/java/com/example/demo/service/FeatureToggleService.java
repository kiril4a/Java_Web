package com.example.demo.service;

import com.example.demo.config.FeatureToggleConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureToggleService {
    private final FeatureToggleConfig featureToggleConfig;

    public boolean isFeatureEnabled(String featureName) {
        return featureToggleConfig.getToggles().getOrDefault(featureName, false);
    }
}
