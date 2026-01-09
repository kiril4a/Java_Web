package com.example.Java_Web.service;

import com.example.Java_Web.aspect.FeatureToggle;
import com.example.Java_Web.domain.model.CosmoCat;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CosmoCatService {

    @FeatureToggle(feature = "cosmoCats")
    public List<CosmoCat> getCosmoCats() {
        return Collections.emptyList();
    }
}
