package com.waa.lab.service;

import com.waa.lab.domain.Log;
import com.waa.lab.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService implements ILogService{

    private LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
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
