package ua.pinger.service.validator;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HostValidatorTest {

    @Test
    public void validate() {
        HostValidator validator = new HostValidator();

        assertTrue(validator.isValid("localhost"));
        assertTrue(validator.isValid("8.8.8.8"));
        assertTrue(validator.isValid("google.com"));

        assertFalse(validator.isValid("http://google.com"));
        assertFalse(validator.isValid("https://google.com"));
        assertFalse(validator.isValid("#@$&(dgf"));
        assertFalse(validator.isValid("nonexisting"));
        assertFalse(validator.isValid("999.9999.999.999"));
    }
}