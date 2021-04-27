package ua.pinger.service.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class URLValidatorTest {

    @Test
    public void isValid() {
        URLValidator validator = new URLValidator();

        assertTrue(validator.isValid("http://google.com"));
        assertTrue(validator.isValid("https://google.com"));

        assertTrue(validator.isValid("https://google.com/"));
        assertTrue(validator.isValid("https://google.com/"));

        assertFalse(validator.isValid("://google.com/"));
        assertFalse(validator.isValid(".com/"));
        assertFalse(validator.isValid("google.com"));
        assertFalse(validator.isValid(".com"));
        assertFalse(validator.isValid("google"));
        assertFalse(validator.isValid("@%&#@*$(45sdf7"));
    }
}