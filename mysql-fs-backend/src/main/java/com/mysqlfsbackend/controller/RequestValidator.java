package com.example.issue76;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * The RequestValidator class provides methods for validating HTTP requests.
 * It includes methods to validate browse paths and browse depths before processing the requests.
 * 
 * ResponseEntity class is part of the Spring Framework and is used to represent the entire HTTP response, 
 * including the status code, headers, and body. It's a generic class, allowing you to define the type of the response body. 
 * Example: ResponseEntity<String>, indicating that the response body will be of type String.
 */
public class RequestValidator {

    /**
     * Validates the incoming HTTP request for browse path and browse depth.
     *
     * @param browsePath  Optional containing the browse path (if present) from the HTTP request.
     * @param browseDepth Optional containing the browse depth (if present) from the HTTP request.
     * @return ResponseEntity with appropriate status and message indicating the validation result.
     */
    public static ResponseEntity<String> validateRequest(Optional<String> browsePath, Optional<Integer> browseDepth) {

        // Check if browsePath is present and is of type String
        if (browsePath.isPresent()) {
            if (browsePath.get() instanceof String) {
                // Check if browsePath is valid using the isValidPath method
                if (!isValidPath(browsePath.get())) {
                    // If invalid path, return a ResponseEntity with BAD_REQUEST status and error message
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid browse path");
                }
            } else {
                // If browsePath is not of type String, return a ResponseEntity with BAD_REQUEST status and error message
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid type for browsePath");
            }
        }
    
        // Check if browseDepth is present and is of type Integer
        if (browseDepth.isPresent()) {
            if (browseDepth.get() instanceof Integer) {
                // Check if browseDepth is valid using the isValidDepth method
                if (!isValidDepth(browseDepth.get())) {
                    // If invalid depth, return a ResponseEntity with BAD_REQUEST status and error message
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid browse depth");
                }
            } else {
                // If browseDepth is not of type Integer, return a ResponseEntity with BAD_REQUEST status and error message
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid type for browseDepth");
            }
        }
    
        // If both browsePath and browseDepth are valid or if they are not present, return a ResponseEntity with OK status and success message
        return ResponseEntity.ok("Request is valid");
    }
    
    /**
     * Validates the browse path using a regular expression pattern.
     *
     * @param path The browse path to be validated.
     * @return True if the path is valid, false otherwise.
     */
    private static boolean isValidPath(String path) {
        // Regular expression to match a valid path format (for example: /folder/file)
        String pathRegex = "^/([a-zA-Z0-9_-]+/?)+$";

        // Check if the path matches the regular expression
        return Pattern.matches(pathRegex, path);
    }

    /**
     * Validates the browse depth to ensure it is within a certain range.
     *
     * @param depth The browse depth to be validated.
     * @return True if the depth is valid, false otherwise.
     */
    private static boolean isValidDepth(int depth) {
        // Check if depth is within a certain range (for example: between 1 and 5 inclusive)
        return depth >= 1 && depth <= 5;
    }
}
