package com.mysqlfsbackend.controller;

import com.mysqlfsbackend.model.dto.BrowseRequestBody;
import com.mysqlfsbackend.model.filesystem.DirectoryEntity;
import com.mysqlfsbackend.model.filesystem.FileEntity;
import com.mysqlfsbackend.model.filesystem.FileSystemObject;
import com.mysqlfsbackend.service.BrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public String getDirectoryContentFromPath(@RequestParam Optional<String> path,
                          @RequestBody Optional<BrowseRequestBody> requestBody) {
        String browsePath = path.orElse("/");
        int depth = requestBody.isPresent() ? requestBody.get().getBrowseDepth() : 3;

        // TODO: Validation

        List<String> directoryPath = Arrays.asList(browsePath.split("/"));
        DirectoryEntity root = browseService.getRoot();
        Optional<DirectoryEntity> workDirectory = browseService.getDirectoryFromPath(root, directoryPath);

        List<List<FileSystemObject>> browseResults;
        if (workDirectory.isPresent()) {
            browseResults = browseService.browseDirectory(workDirectory.get(), depth);
        } else {
            browseResults = new ArrayList<>();
        }

        return browseResults.toString();
    }

    @GetMapping(value = {"/api/browse/files"})
    public String getFileContentFromPath(@RequestParam String path) {
        List<String> filePath = Arrays.asList(path.split("/"));
        DirectoryEntity root = browseService.getRoot();

        // TODO: Validation

        Optional<FileEntity> targetFile = browseService.getFileFromPath(root, filePath);

        if (targetFile.isEmpty()) {
            return "File not found";
        }

        return targetFile.get().getContent();
    }
}
