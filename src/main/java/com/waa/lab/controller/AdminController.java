package com.waa.lab.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @GetMapping
    ResponseEntity<?> findAll() {
        return ResponseEntity.ok().body("Test admin path");
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findById(@PathVariable long id) {
        return ResponseEntity.ok().body("Test admin value");
    }
}
