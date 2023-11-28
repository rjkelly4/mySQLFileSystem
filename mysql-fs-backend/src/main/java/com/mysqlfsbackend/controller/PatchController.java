package com.mysqlfsbackend.controller;

import com.mysqlfsbackend.model.dto.http.PatchBody;
import com.mysqlfsbackend.service.PatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PatchMapping;

@RestController
public class PatchController {
    private final PatchService patchService;

    @Autowired
    public PatchController(PatchService patchService) {
        this.patchService = patchService;
    }

    @PatchMapping(value = {"/api/patchDirectoryParent"})
    public ResponseEntity<Object> PatchDirectoryParent(@RequestBody PatchBody patchBody) {

        patchService.patchDirParent(patchBody.getModifiedField(), patchBody.getId());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PatchMapping(value = {"/api/patchFileParent"})
    public ResponseEntity<Object> patchFileParent(@RequestBody PatchBody patchBody) {

        patchService.patchFileParent(patchBody.getModifiedField(), patchBody.getId());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PatchMapping(value = {"/api/patchDirName"})
    public ResponseEntity<Object> patchDirName(@RequestBody PatchBody patchBody) {

        patchService.patchDirName(patchBody.getModifiedField(), patchBody.getId());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PatchMapping(value = {"/api/patchFileName"})
    public ResponseEntity<Object> patchFileName(@RequestBody PatchBody patchBody) {

        patchService.patchFileName(patchBody.getModifiedField(), patchBody.getId());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}