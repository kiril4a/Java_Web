package com.example.Java_Web.aspect;

import com.example.Java_Web.service.FeatureToggleService;
import com.example.Java_Web.exception.FeatureNotAvailableException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FeatureToggleAspect {

    private final FeatureToggleService featureToggleService;

    public FeatureToggleAspect(FeatureToggleService featureToggleService) {
        this.featureToggleService = featureToggleService;
    }

    @Around("@annotation(featureToggle)")
    public Object checkFeatureToggle(ProceedingJoinPoint joinPoint, FeatureToggle featureToggle) throws Throwable {
        String featureName = featureToggle.value();
        if (!featureToggleService.isFeatureEnabled(featureName)) {
            throw new FeatureNotAvailableException("Feature " + featureName + " is not available");
        }
        return joinPoint.proceed();
    }
}
