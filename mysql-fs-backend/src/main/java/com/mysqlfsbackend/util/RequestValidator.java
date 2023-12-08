package com.mysqlfsbackend.util;

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
     * Validates the browse request content - browse path and browse depth.
     *
     * @param browsePath The browse path from the HTTP request.
     * @param browseDepth The browse depth from the HTTP request.
     * @return Appropriate error message (if any) indicating the validation result.
     */
    public static Optional<String> validateBrowseRequest(String browsePath, Integer browseDepth) {
        // Check if browsePath is valid using the isValidPath method
        if (!isValidPath(browsePath, true)) {
            return Optional.of("Invalid browse path");
        }
        // Check if browseDepth is valid using the isValidDepth method
        if (!isValidDepth(browseDepth)) {
            return Optional.of("Invalid browse depth");
        }
        return Optional.empty();
    }

    /**
     * Validates the browse request content - browse path and browse depth.
     *
     * @param path The browse path from the HTTP request.
     * @return Appropriate error message (if any) indicating the validation result.
     */
    public static Optional<String> validateBrowseRequest(String path) {
        // Check if browsePath is valid using the isValidPath method
        if (!isValidPath(path, false)) {
            return Optional.of("Invalid file path");
        }
        return Optional.empty();
    }

    /**
     * Check path format for directories and files
     *
     * @param path the directory or file path that will be checked
     * @param isDir whether it is a directory path
     * @return true if the path is valid, otherwise false
     */
    private static boolean isValidPath(String path, boolean isDir) {
        String pathRegex = isDir ? "^([a-zA-Z0-9_-]*/?)+$" : "^([a-zA-Z0-9_-]+/?)+$";
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
        return depth >= 1 && depth <= 20;
    }
}


