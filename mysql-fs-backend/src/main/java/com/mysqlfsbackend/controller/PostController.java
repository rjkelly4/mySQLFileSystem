package com.mysqlfsbackend.controller;

import com.mysqlfsbackend.model.dto.http.PostBody;
import com.mysqlfsbackend.model.filesystem.DirectoryEntity;
import com.mysqlfsbackend.repository.DirectoryDao;
import com.mysqlfsbackend.service.PatchService;
import com.mysqlfsbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.ArrayList;
import java.util.Optional;

@RestController
public class PostController {
    private final PostService postService;
    private final PatchService patchService;

    @Autowired
    public PostController(PostService postService, PatchService patchService) {
        this.postService = postService;
        this.patchService = patchService;
    }

    @PostMapping(value = {"/api/postDirectory"})
    public ResponseEntity<Object> postDirectory(@RequestBody PostBody postBody) {

        postService.postDirectory(postBody.getName(), postBody.getParentId(), postBody.getPermission(),
                                    postBody.getOwnerUserId(), postBody.getOwnerGroupId(), postBody.getSize());

        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PostMapping(value = {"/api/postFile"})
    public ResponseEntity<Object> postFile(@RequestBody PostBody postBody) {

        postService.postFile(postBody.getName(), postBody.getParentId(), postBody.getPermission(),
                postBody.getOwnerUserId(), postBody.getOwnerGroupId(), postBody.getSize(),
                postBody.getFileType(), postBody.getContent());

        Optional<DirectoryEntity> parentDirOptional = postService.getParentDir(postBody.getParentId());

        while (parentDirOptional.isPresent()) {
            DirectoryEntity parentDir = parentDirOptional.get();
            if (parentDir.getId() != null)
                patchService.patchDirSize(String.valueOf(parentDir.getSize() + Integer.parseInt(postBody.getSize())), parentDir.getId());

            if (parentDir.getParentDirId() != null)
                parentDirOptional = postService.getParentDir(parentDir.getParentDirId());
            else break;
        }

        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}