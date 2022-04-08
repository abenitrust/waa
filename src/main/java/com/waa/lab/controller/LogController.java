package com.waa.lab.controller;

import com.waa.lab.domain.Log;
import com.waa.lab.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logs")
public class LogController {
    private LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public List<Log> findAll() {
        return logService.findAll();
    }

    @GetMapping("/{id}")
    public Log findById(long id) {
        return logService.findById(id);
    }
}
