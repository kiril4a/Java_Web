package com.example.Java_Web.service;

import com.example.Java_Web.exception.FeatureNotAvailableException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CosmoCatServiceTest {

    @Autowired
    private CosmoCatService cosmoCatService;

    @MockBean
    private FeatureToggleService featureToggleService;

    @Test
    public void testGetCosmoCats_FeatureEnabled() {
        when(featureToggleService.isFeatureEnabled("cosmoCats")).thenReturn(true);
        assertDoesNotThrow(() -> cosmoCatService.getCosmoCats());
    }

    @Test
    public void testGetCosmoCats_FeatureDisabled() {
        when(featureToggleService.isFeatureEnabled("cosmoCats")).thenReturn(false);
        assertThrows(FeatureNotAvailableException.class, () -> cosmoCatService.getCosmoCats());
    }
}
