package com.mysqlfsbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {
    @GetMapping("/api")
    public String pingApi() {
        return "This is the API for MySQL File System Project";
    }
}
