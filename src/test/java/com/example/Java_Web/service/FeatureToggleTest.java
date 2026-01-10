package com.example.Java_Web.service;

import com.example.Java_Web.exception.FeatureNotAvailableException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class FeatureToggleTest {

    @Autowired
    private CosmoCatService cosmoCatService;

    @Test
    @TestPropertySource(properties = "feature.cosmoCats.enabled=true")
    void whenFeatureIsEnabled_thenMethodExecutes() {
        assertThat(cosmoCatService.getCosmoCats()).isNotEmpty();
    }

    @Test
    @TestPropertySource(properties = "feature.cosmoCats.enabled=false")
    void whenFeatureIsDisabled_thenExceptionIsThrown() {
        assertThrows(FeatureNotAvailableException.class, () -> {
            cosmoCatService.getCosmoCats();
        });
    }
}
