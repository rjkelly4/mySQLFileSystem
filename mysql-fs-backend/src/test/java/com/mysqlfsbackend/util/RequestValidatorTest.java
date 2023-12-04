package com.mysqlfsbackend.util;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RequestValidatorTest {
    @Test
    public void testValidRequest() {
        String validPath = "folder/file";
        Integer validDepth = 5;

        Optional<String> errorMessage = RequestValidator.validateBrowseRequest(validPath, validDepth);

        assertFalse(errorMessage.isPresent());
    }

    @Test
    public void testInvalidPath() {
        String invalidPath = "folder/file!";
        Integer validDepth = 5;

        Optional<String> errorMessage = RequestValidator.validateBrowseRequest(invalidPath, validDepth);

        assertTrue(errorMessage.isPresent());
        assertEquals("Invalid browse path", errorMessage.get());
    }

    @Test
    public void testInvalidDepth() {
        String validPath = "folder/file";
        Integer invalidDepth = 15;

        Optional<String> errorMessage = RequestValidator.validateBrowseRequest(validPath, invalidDepth);

        assertTrue(errorMessage.isPresent());
        assertEquals("Invalid browse depth", errorMessage.get());
    }

    @Test
    public void testValidPathInvalidDepth() {
        String validPath = "";
        Integer invalidDepth = 0;

        Optional<String> errorMessage = RequestValidator.validateBrowseRequest(validPath, invalidDepth);

        assertTrue(errorMessage.isPresent());
        assertEquals("Invalid browse depth", errorMessage.get());
    }
}