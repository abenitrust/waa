package com.waa.lab.service.impl;

import com.waa.lab.domain.Log;
import com.waa.lab.repository.LogRepository;
import com.waa.lab.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    private LogRepository logRepository;

    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void save(Log log) {
        logRepository.save(log);
    }

    @Override
    public Log findById(long id) {
        return logRepository.findById(id).orElse(null);
    }

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }
}
