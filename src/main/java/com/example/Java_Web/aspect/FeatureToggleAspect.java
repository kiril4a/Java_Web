package com.example.Java_Web.aspect;

import com.example.Java_Web.exception.FeatureNotAvailableException;
import com.example.Java_Web.service.FeatureToggleService;
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
        if (!featureToggleService.isFeatureEnabled(featureToggle.feature())) {
            throw new FeatureNotAvailableException("Feature " + featureToggle.feature() + " is currently disabled.");
        }
        return joinPoint.proceed();
    }
}
