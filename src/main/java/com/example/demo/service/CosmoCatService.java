package com.example.demo.service;

import com.example.demo.aspect.FeatureToggle;
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
