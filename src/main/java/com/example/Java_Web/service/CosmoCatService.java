package com.example.Java_Web.service;

import com.example.Java_Web.aspect.FeatureToggle;
import com.example.Java_Web.domain.model.CosmoCat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CosmoCatService {

    @FeatureToggle("cosmoCats")
    public List<CosmoCat> getCosmoCats() {
        List<CosmoCat> cats = new ArrayList<>();
        cats.add(new CosmoCat("Barsik", "Pilot"));
        cats.add(new CosmoCat("Murzik", "Navigator"));
        return cats;
    }
}
