package com.example.issue76;

import org.springframework.http.ResponseEntity;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class RequestValidatorTest {

    @Test
    public void testValidRequest() {
        Optional<String> validPath = Optional.of("/folder/file");
        Optional<Integer> validDepth = Optional.of(5);

        ResponseEntity<String> response = RequestValidator.validateRequest(validPath, validDepth);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals("Request is valid", response.getBody());
    }

    @Test
    public void testInvalidPath() {
        Optional<String> invalidPath = Optional.of("/folder/file!");
        Optional<Integer> validDepth = Optional.of(5);

        ResponseEntity<String> response = RequestValidator.validateRequest(invalidPath, validDepth);

        Assert.assertEquals(400, response.getStatusCodeValue());
        Assert.assertEquals("Invalid browse path", response.getBody());
    }

    @Test
    public void testInvalidDepth() {
        Optional<String> validPath = Optional.of("/folder/file");
        Optional<Integer> invalidDepth = Optional.of(15);

        ResponseEntity<String> response = RequestValidator.validateRequest(validPath, invalidDepth);

        Assert.assertEquals(400, response.getStatusCodeValue());
        Assert.assertEquals("Invalid browse depth", response.getBody());
    }

    @Test
    public void testValidPathInvalidDepth() {
        Optional<String> validPath = Optional.of("/folder/file");
        Optional<Integer> invalidDepth = Optional.of(0);

        ResponseEntity<String> response = RequestValidator.validateRequest(validPath, invalidDepth);

        Assert.assertEquals(400, response.getStatusCodeValue());
        Assert.assertEquals("Invalid browse depth", response.getBody());
    }

    @Test
    public void testNoPathAndDepth() {
        Optional<String> emptyPath = Optional.empty();
        Optional<Integer> emptyDepth = Optional.empty();

        ResponseEntity<String> response = RequestValidator.validateRequest(emptyPath, emptyDepth);

        Assert.assertEquals(200, response.getStatusCodeValue());
        Assert.assertEquals("Request is valid", response.getBody());
    }
}
