package com.example.Java_Web.service;

import com.example.Java_Web.aspect.FeatureToggle;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CosmoCatService {

    @FeatureToggle(featureName = "cosmoCats.enabled")
    public List<String> getCosmoCats() {
        return Arrays.asList("Cat1", "Cat2", "Cat3");
    }
}
