package com.example.demo.service;

import com.example.demo.exception.FeatureNotAvailableException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CosmoCatServiceTest {

    @Autowired
    private CosmoCatService cosmoCatService;

    @Test
    @TestPropertySource(properties = "feature.cosmoCats.enabled=true")
    void whenFeatureIsEnabled_shouldReturnCats() {
        assertThat(cosmoCatService.getCosmoCats()).isNotEmpty();
    }

    @Test
    @TestPropertySource(properties = "feature.cosmoCats.enabled=false")
    void whenFeatureIsDisabled_shouldThrowException() {
        assertThrows(FeatureNotAvailableException.class, () -> cosmoCatService.getCosmoCats());
    }
}
