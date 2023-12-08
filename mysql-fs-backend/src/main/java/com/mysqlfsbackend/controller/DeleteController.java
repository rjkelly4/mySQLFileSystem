package com.mysqlfsbackend.controller;

import com.mysqlfsbackend.model.dto.http.DeleteBody;
import com.mysqlfsbackend.service.DeleteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
public class DeleteController {
    private final DeleteService DeleteService;

    @Autowired
    public DeleteController(DeleteService DeleteService) {
        this.DeleteService = DeleteService;
    }

    @DeleteMapping(value = {"/api/deleteDirectory"})
    public ResponseEntity<Object> DeleteDirectory(@RequestBody DeleteBody deleteBody) {

        DeleteService.deleteDirectory(deleteBody.getId());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @DeleteMapping(value = {"/api/deleteFile"})
    public ResponseEntity<Object> DeleteFile(@RequestBody DeleteBody deleteBody) {

        DeleteService.deleteFile(deleteBody.getId());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}