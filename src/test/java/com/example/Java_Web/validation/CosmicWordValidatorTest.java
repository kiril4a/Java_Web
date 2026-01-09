package com.example.Java_Web.validation;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CosmicWordValidatorTest {

    private CosmicWordValidator validator;

    @Mock
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validator = new CosmicWordValidator();
    }

    @Test
    void testValid_WithStar() {
        assertTrue(validator.isValid("Shining star product", context));
    }

    @Test
    void testValid_WithGalaxy() {
        assertTrue(validator.isValid("Galaxy explorer", context));
    }

    @Test
    void testValid_WithComet() {
        assertTrue(validator.isValid("Comet tail special", context));
    }

    @Test
    void testValid_CaseInsensitive() {
        assertTrue(validator.isValid("STAR WARS", context));
        assertTrue(validator.isValid("Galaxy", context));
        assertTrue(validator.isValid("COMET", context));
    }

    @Test
    void testInvalid_NoCosmicWord() {
        assertFalse(validator.isValid("Regular product", context));
    }

    @Test
    void testInvalid_Null() {
        assertFalse(validator.isValid(null, context));
    }

    @Test
    void testInvalid_Blank() {
        assertFalse(validator.isValid("", context));
        assertFalse(validator.isValid("   ", context));
    }

    @Test
    void testValid_MultipleCosmicWords() {
        assertTrue(validator.isValid("Star and galaxy together", context));
    }
}
