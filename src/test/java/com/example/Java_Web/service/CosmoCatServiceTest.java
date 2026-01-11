package com.example.Java_Web.service;

import com.example.Java_Web.exception.FeatureNotAvailableException;
import com.example.Java_Web.config.FeatureToggleConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@TestPropertySource(properties = {
        "spring.datasource.url=jdbc:h2:mem:testdb",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.datasource.username=sa",
        "spring.datasource.password=password",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect"
})
class CosmoCatServiceTest {

    @Autowired
    private CosmoCatService cosmoCatService;

    @Autowired
    private FeatureToggleConfig featureToggleConfig;

    @Test
    void getCosmoCats_whenFeatureIsEnabled_shouldReturnCats() {
        Map<String, Boolean> cosmoCats = new HashMap<>();
        cosmoCats.put("enabled", true);
        featureToggleConfig.setCosmoCats(cosmoCats);

        assertFalse(cosmoCatService.getCosmoCats().isEmpty());
    }

    @Test
    void getCosmoCats_whenFeatureIsDisabled_shouldThrowException() {
        Map<String, Boolean> cosmoCats = new HashMap<>();
        cosmoCats.put("enabled", false);
        featureToggleConfig.setCosmoCats(cosmoCats);

        assertThrows(FeatureNotAvailableException.class, () -> {
            cosmoCatService.getCosmoCats();
        });
    }
}
