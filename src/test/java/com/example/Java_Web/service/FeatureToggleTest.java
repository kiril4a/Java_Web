package com.example.Java_Web.service;

import com.example.Java_Web.aspect.FeatureToggleAspect;
import com.example.Java_Web.config.FeatureToggleConfig;
import com.example.Java_Web.exception.FeatureNotAvailableException;
import com.example.Java_Web.service.CosmoCatService;
import com.example.Java_Web.service.FeatureToggleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {FeatureToggleConfig.class, FeatureToggleService.class, FeatureToggleAspect.class, CosmoCatService.class})
@TestPropertySource(properties = {
        "feature.cosmoCats.enabled=true",
        "spring.autoconfigure.exclude=" +
                "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration," +
                "org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration," +
                "org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration"
})
class FeatureToggleEnabledTest {

    @Autowired
    private CosmoCatService cosmoCatService;

    @Test
    void whenFeatureIsEnabled_thenMethodExecutes() {
        assertThat(cosmoCatService.getCosmoCats()).isNotEmpty();
    }
}
