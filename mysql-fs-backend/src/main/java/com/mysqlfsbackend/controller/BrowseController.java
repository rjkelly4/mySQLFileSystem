package com.mysqlfsbackend.controller;

import com.mysqlfsbackend.service.BrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BrowseController {
    private final BrowseService browseService;

    @Autowired
    public BrowseController(BrowseService browseService) {
        this.browseService = browseService;
    }

    @GetMapping(value = {"/api/browse", "/api/browse/{browsePath}"})
    public String getPath(@PathVariable Optional<String> browsePath,
                          @RequestParam("browseDepth") Optional<Integer> browseDepth) {
        String path = browsePath.isEmpty() ? "/" : browsePath.get();
        int depth = browseDepth.isEmpty() ? 3 : browseDepth.get();

        // TODO: Call browse service method

        return "You've made a GET request for " + path + " with a depth of " + depth;
    }
}
