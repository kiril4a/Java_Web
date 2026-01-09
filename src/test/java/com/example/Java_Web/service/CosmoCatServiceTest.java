package com.example.Java_Web.service;

import com.example.Java_Web.config.FeatureToggleConfig;
import com.example.Java_Web.exception.FeatureNotAvailableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootTest
@EnableAspectJAutoProxy
public class CosmoCatServiceTest {

    @Autowired
    private CosmoCatService cosmoCatService;

    @MockBean
    private FeatureToggleService featureToggleService;

    @Test
    public void testGetCosmoCats_Enabled() {
        Mockito.when(featureToggleService.isFeatureEnabled("cosmoCats")).thenReturn(true);
        Assertions.assertDoesNotThrow(() -> cosmoCatService.getCosmoCats());
        Assertions.assertFalse(cosmoCatService.getCosmoCats().isEmpty());
    }

    @Test
    public void testGetCosmoCats_Disabled() {
        Mockito.when(featureToggleService.isFeatureEnabled("cosmoCats")).thenReturn(false);
        Assertions.assertThrows(FeatureNotAvailableException.class, () -> cosmoCatService.getCosmoCats());
    }
}
