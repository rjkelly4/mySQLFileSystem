package com.mysqlfsbackend.controller;

import com.mysqlfsbackend.model.dto.filesystem.DirectoryDto;
import com.mysqlfsbackend.model.dto.filesystem.FileSystemObjectDtoFactory;
import com.mysqlfsbackend.model.dto.filesystem.FileDto;
import com.mysqlfsbackend.model.dto.http.BrowseRequestBody;
import com.mysqlfsbackend.model.filesystem.DirectoryEntity;
import com.mysqlfsbackend.model.filesystem.FileEntity;
import com.mysqlfsbackend.model.filesystem.FileSystemObject;
import com.mysqlfsbackend.service.BrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class BrowseController {
    private final BrowseService browseService;

    @Autowired
    public BrowseController(BrowseService browseService) {
        this.browseService = browseService;
    }

    @GetMapping(value = {"/api/browse/folders"})
    public ResponseEntity<DirectoryDto> getDirectoryContentFromPath(@RequestParam Optional<String> path,
                                                                    @RequestBody Optional<BrowseRequestBody> requestBody) {
        String browsePath = path.orElse("/");
        int depth = requestBody.isPresent() ? requestBody.get().getBrowseDepth() : 3;

        // TODO: Validation

        List<String> directoryPath = Arrays.asList(browsePath.split("/"));
        DirectoryEntity root = browseService.getRoot();
        Optional<DirectoryEntity> workDirectory = browseService.getDirectoryFromPath(root, directoryPath);

        List<List<FileSystemObject>> browseResults;

        // TODO: Add message to response body
        if (workDirectory.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            browseResults = browseService.browseDirectory(workDirectory.get(), depth);

            return new ResponseEntity<>(
                    FileSystemObjectDtoFactory.generateDirectoryDto(workDirectory.get(), browseResults),
                    HttpStatus.OK
            );
        }
    }

    @GetMapping(value = {"/api/browse/files"})
    public ResponseEntity<FileDto> getFileContentFromPath(@RequestParam String path) {
        List<String> filePath = Arrays.asList(path.split("/"));
        DirectoryEntity root = browseService.getRoot();

        // TODO: Validation

        Optional<FileEntity> targetFile = browseService.getFileFromPath(root, filePath);

        // TODO: Add message to response body
        if (targetFile.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>((FileDto) FileSystemObjectDtoFactory.convertToDto(targetFile.get()),
                HttpStatus.OK
        );
    }
}
