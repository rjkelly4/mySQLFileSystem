package com.mysqlfsbackend.controller;

import com.mysqlfsbackend.model.dto.filesystem.DirectoryDto;
import com.mysqlfsbackend.model.dto.filesystem.FileSystemObjectDtoFactory;
import com.mysqlfsbackend.model.dto.filesystem.FileDto;
import com.mysqlfsbackend.model.dto.http.BrowseRequestBody;
import com.mysqlfsbackend.model.filesystem.DirectoryEntity;
import com.mysqlfsbackend.model.filesystem.FileEntity;
import com.mysqlfsbackend.model.filesystem.FileSystemObject;
import com.mysqlfsbackend.repository.DirectoryDao;
import com.mysqlfsbackend.service.BrowseService;
import com.mysqlfsbackend.service.PutService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
public class PutController {
    private final PutService putService;

    @Autowired
    public PutController(PutService putService) {
        this.putService = putService;
    }

    @PutMapping(value = {"/test"})
    public String test(@RequestParam Integer id){
        return ("All set!" + String.valueOf(id));
    }

    @PutMapping(value = {"/put"})
    public ResponseEntity<Object> putNewDirectory(@RequestParam String fields) {
        String[] newFields = fields.split(",");
        putService.putNewDirectory(newFields[0], newFields[1],
                Integer.parseInt(newFields[2]), newFields[3], newFields[4],
                Integer.parseInt(newFields[5]));


        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}