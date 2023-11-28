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

        patchService.patchDirParent(patchBody.getNewParentDirId().toString(), patchBody.getId().toString());
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PatchMapping(value = {"/api/patchFileParent"})
    public ResponseEntity<Object> patchFileParent(@RequestParam String newParentDirId,
                                                @RequestParam String id) {

        patchService.patchFileParent(newParentDirId, id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PatchMapping(value = {"/api/patchDirName"})
    public ResponseEntity<Object> patchDirName(@RequestParam String newName,
            @RequestParam String id) {

        patchService.patchDirName(newName, id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PatchMapping(value = {"/api/patchFileName"})
    public ResponseEntity<Object> patchFileName(@RequestParam String newName,
                                             @RequestParam String id) {

        patchService.patchFileName(newName, id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}