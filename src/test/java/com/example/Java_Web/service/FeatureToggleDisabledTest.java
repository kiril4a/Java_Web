package com.example.Java_Web.service;

import com.example.Java_Web.aspect.FeatureToggleAspect;
import com.example.Java_Web.config.FeatureToggleConfig;
import com.example.Java_Web.exception.FeatureNotAvailableException;
import com.example.Java_Web.service.CosmoCatService;
import com.example.Java_Web.service.FeatureToggleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {FeatureToggleConfig.class, FeatureToggleService.class, FeatureToggleAspect.class, CosmoCatService.class, AopAutoConfiguration.class})
@EnableConfigurationProperties(FeatureToggleConfig.class)
@TestPropertySource(properties = {
        "feature.cosmoCats.enabled=false",
        "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration,org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration,org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration,org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration"
})
class FeatureToggleDisabledTest {

    @Autowired
    private CosmoCatService cosmoCatService;

    @Test
    void whenFeatureIsDisabled_thenExceptionIsThrown() {
        assertThrows(FeatureNotAvailableException.class, () -> cosmoCatService.getCosmoCats());
    }
}
