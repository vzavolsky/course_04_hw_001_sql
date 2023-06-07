package com.skypro.course_03.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/info")
@Tag(name = "Info UI", description = "Check your system info.")
public class InfoController {

    private final Integer port;


    public InfoController(@Value("${server.port}") Integer port) {
        this.port = port;
    }

    @GetMapping(path = "/port")
    public ResponseEntity<Integer> getPort() {
        return ResponseEntity.ok(port);
    }
}