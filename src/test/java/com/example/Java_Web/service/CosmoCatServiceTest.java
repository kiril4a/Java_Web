package com.example.Java_Web.service;

import com.example.Java_Web.exception.FeatureNotAvailableException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CosmoCatServiceTest {

    @Nested
    @SpringBootTest
    @TestPropertySource(properties = "feature.toggles.cosmoCats=true")
    class WhenFeatureIsEnabled {

        @Autowired
        private CosmoCatService cosmoCatService;

        @Test
        void shouldExecuteMethod() {
            assertDoesNotThrow(() -> cosmoCatService.getCosmoCats());
        }
    }

    @Nested
    @SpringBootTest
    @TestPropertySource(properties = "feature.toggles.cosmoCats=false")
    class WhenFeatureIsDisabled {

        @Autowired
        private CosmoCatService cosmoCatService;

        @Test
        void shouldThrowException() {
            assertThrows(FeatureNotAvailableException.class, () -> {
                cosmoCatService.getCosmoCats();
            });
        }
    }
}
