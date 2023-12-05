package com.mysqlfsbackend.controller;

import com.mysqlfsbackend.model.dto.filesystem.DirectoryDto;
import com.mysqlfsbackend.model.dto.filesystem.FileSystemObjectDtoFactory;
import com.mysqlfsbackend.model.dto.filesystem.FileDto;
import com.mysqlfsbackend.model.dto.http.BrowseRequestBody;
import com.mysqlfsbackend.model.dto.http.ResponseBody;
import com.mysqlfsbackend.model.filesystem.DirectoryEntity;
import com.mysqlfsbackend.model.filesystem.FileEntity;
import com.mysqlfsbackend.model.filesystem.FileSystemObject;
import com.mysqlfsbackend.service.BrowseService;
import com.mysqlfsbackend.util.RequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping(value = {"/api/browse/folders"})
    public ResponseEntity<ResponseBody<DirectoryDto>> getDirectoryContentFromPath(@RequestParam Optional<String> path,
                                                                    @RequestBody Optional<BrowseRequestBody> requestBody) {
        HttpStatus status;
        ResponseBody<DirectoryDto> responseBody = new ResponseBody<>();
        String browsePath = path.orElse("");
        if (!browsePath.isEmpty() && browsePath.charAt(0) == '/') {
            browsePath = browsePath.substring(1);
        }
        int depth = requestBody.isPresent() ? requestBody.get().getBrowseDepth() : 3;

        // Validation
        Optional<String> validationError = RequestValidator.validateBrowseRequest(browsePath, depth);
        if (validationError.isPresent()) {
            status = HttpStatus.BAD_REQUEST;
            responseBody.setMessage(validationError.get());
            return new ResponseEntity<>(responseBody, status);
        }

        // Process request
        List<String> directoryPath = Arrays.asList(browsePath.split("/"));
        DirectoryEntity root = browseService.getRoot();
        Optional<DirectoryEntity> workDirectory = browseService.getDirectoryFromPath(root, directoryPath);

        if (workDirectory.isEmpty()) {
            status = HttpStatus.BAD_REQUEST;
            responseBody.setMessage("Content not found");
        } else {
            List<List<FileSystemObject>> browseResults = browseService.browseDirectory(workDirectory.get(), depth);
            DirectoryDto result = FileSystemObjectDtoFactory.generateDirectoryDto(workDirectory.get(), browseResults);

            status = HttpStatus.OK;
            responseBody.setMessage("Browse successful");
            responseBody.setPayload(result);
        }

        return new ResponseEntity<>(responseBody, status);
    }

    @GetMapping(value = {"/api/browse/files"})
    public ResponseEntity<ResponseBody<FileDto>> getFileContentFromPath(@RequestParam String path) {
        HttpStatus status;
        ResponseBody<FileDto> responseBody = new ResponseBody<>();
        if (!path.isEmpty() && path.charAt(0) == '/') {
            path = path.substring(1);
        }

        // Validation
        Optional<String> validationError = RequestValidator.validateBrowseRequest(path);
        if (validationError.isPresent()) {
            status = HttpStatus.BAD_REQUEST;
            responseBody.setMessage(validationError.get());
            return new ResponseEntity<>(responseBody, status);
        }

        List<String> filePath = Arrays.asList(path.split("/"));
        if (!filePath.isEmpty() && filePath.get(0).isEmpty()) {
            filePath.remove(0);
        }

        DirectoryEntity root = browseService.getRoot();
        Optional<FileEntity> targetFile = browseService.getFileFromPath(root, filePath);

        if (targetFile.isEmpty()) {
            status = HttpStatus.BAD_REQUEST;
            responseBody.setMessage("File not found");
        } else {
            status = HttpStatus.OK;
            responseBody.setMessage("File found");
            responseBody.setPayload((FileDto) FileSystemObjectDtoFactory.convertToDto(targetFile.get()));
        }

        return new ResponseEntity<>(responseBody, status);
    }
}
