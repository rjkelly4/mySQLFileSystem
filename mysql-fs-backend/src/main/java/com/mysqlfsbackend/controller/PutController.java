package com.mysqlfsbackend.controller;

import com.mysqlfsbackend.service.PutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class PutController {
    private final PutService PutService;

    @Autowired
    public PutController(PutService PutService) {
        this.PutService = PutService;
    }

    @PutMapping(value = {"/api/putDirectoryParent"})
    public ResponseEntity<Object> PutDirectoryParent(@RequestParam String newParentDirId,
                                                     @RequestParam String id) {

        PutService.putDirParent(newParentDirId, id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PutMapping(value = {"/api/putFileParent"})
    public ResponseEntity<Object> putFileParent(@RequestParam String newParentDirId,
                                                @RequestParam String id) {

        PutService.putFileParent(newParentDirId, id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}