package com.mysqlfsbackend.controller;

import com.mysqlfsbackend.model.filesystem.DirectoryEntity;
import com.mysqlfsbackend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = {"/test"})
    public String test(@RequestParam Integer id){
        return ("All set!" + String.valueOf(id));
    }

    @PostMapping(value = {"/postDirectory"})
    public ResponseEntity<Object> postDirectory(@RequestParam String name, @RequestParam
                                                  String parentDirId, @RequestParam int permission,
                                                  @RequestParam String ownerUserId, @RequestParam String
                                                  ownerGroupId, @RequestParam int size) {
        DirectoryEntity newDir = new DirectoryEntity(null, name, parentDirId, permission, ownerUserId, ownerGroupId,
                size);

        postService.postDirectory(name, parentDirId, permission, ownerUserId, ownerGroupId, size);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PostMapping(value = {"/postFile"})
    public ResponseEntity<Object> postFile(@RequestParam String name, @RequestParam String parentDirId,
                                           @RequestParam int permission, @RequestParam String ownerUserId,
                                           @RequestParam String ownerGroupId, @RequestParam int size,
                                           @RequestParam String fileType, @RequestParam String content) {
        postService.postFile(name, parentDirId, permission, ownerUserId, ownerGroupId, size, fileType,
                content);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}