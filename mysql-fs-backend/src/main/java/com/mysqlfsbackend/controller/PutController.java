package com.mysqlfsbackend.controller;

import com.mysqlfsbackend.model.dto.http.PostBody;
import com.mysqlfsbackend.service.PutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PutController {
    private final PutService putService;

    @Autowired
    public PutController(PutService putService) {
        this.putService = putService;
    }


    @PutMapping(value = {"/api/putDirectory"})
    //As the params are identical, this uses a PostBody object.
    public ResponseEntity<Object> putDirectory(@RequestBody PostBody postBody) {

        putService.putDirectory(postBody.getId(), postBody.getName(), postBody.getParentId(), postBody.getPermission(),
                postBody.getOwnerUserId(), postBody.getOwnerGroupId(), postBody.getSize());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PutMapping(value = {"/api/putFile"})
    //As the params are identical, this uses a PostBody object.
    public ResponseEntity<Object> putFile(@RequestBody PostBody postBody) {

        putService.putFile(postBody.getId(), postBody.getName(), postBody.getParentId(), postBody.getPermission(),
                postBody.getOwnerUserId(), postBody.getOwnerGroupId(), postBody.getSize(),
                postBody.getFileType(), postBody.getContent());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
