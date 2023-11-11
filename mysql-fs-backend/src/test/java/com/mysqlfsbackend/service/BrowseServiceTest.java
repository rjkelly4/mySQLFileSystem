package com.mysqlfsbackend.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class BrowseServiceTest {
    @Autowired
    private BrowseService browseService;

    @Test
    @Rollback
    void testGetDirectoryFromPath() {
        // Implement a test for getDirectoryFromPath
    }

    @Test
    @Rollback
    void testGetFileFromPath() {
        // Implement a test for getFileFromPath
    }

    @Test
    @Rollback
    void testBrowseDirectory() {
        // Implement a test for browseDirectory
    }
}
