package com.example.demo.aspect;

import com.example.demo.exception.FeatureNotAvailableException;
import com.example.demo.service.FeatureToggleService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class FeatureToggleAspect {
    private final FeatureToggleService featureToggleService;

    @Around("@annotation(com.example.demo.aspect.FeatureToggle)")
    public Object featureToggle(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        FeatureToggle featureToggle = method.getAnnotation(FeatureToggle.class);

        if (featureToggleService.isFeatureEnabled(featureToggle.featureName())) {
            return joinPoint.proceed();
        } else {
            throw new FeatureNotAvailableException("Feature " + featureToggle.featureName() + " is not available.");
        }
    }
}
