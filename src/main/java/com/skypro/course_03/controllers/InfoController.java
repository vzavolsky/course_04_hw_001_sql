package com.skypro.course_03.controllers;

import com.skypro.course_03.services.InfoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/info")
@Tag(name = "Info UI", description = "Check your system info.")
public class InfoController {

    private final InfoService infoService;

    private final Integer port;

    public InfoController(InfoService infoService, @Value("${server.port}") Integer port) {
        this.infoService = infoService;
        this.port = port;
    }

    @GetMapping(path = "/port")
    public ResponseEntity<Integer> getPort() {
        return ResponseEntity.ok(port);
    }

    @GetMapping
    public ResponseEntity checkParallelStream() {
        infoService.checkParallelStream();
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/compare")
    public ResponseEntity compareParallelAndPlainStream() {
        infoService.compareParallelAndPlainStream();
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/thread")
    public ResponseEntity threadsOfStudents() {
        infoService.threadsOfStudents();
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/syncthread")
    public ResponseEntity syncThreadsOfStudents() {
        infoService.syncThreadsOfStudents();
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/altthread")
    public ResponseEntity altThreadsOfStudents() {
        infoService.printStudents();
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/altsyncthread")
    public ResponseEntity altSyncThreadsOfStudents() {
        infoService.printStudentsSync();
        return ResponseEntity.ok().build();
    }
}
