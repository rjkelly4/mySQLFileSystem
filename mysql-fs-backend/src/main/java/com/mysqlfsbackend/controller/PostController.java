package com.mysqlfsbackend.controller;

import com.mysqlfsbackend.model.dto.http.PostBody;
import com.mysqlfsbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
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
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}