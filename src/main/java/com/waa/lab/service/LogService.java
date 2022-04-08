package com.waa.lab.service;

import com.waa.lab.domain.Log;

import java.util.List;

public interface LogService {
    void save(Log log);
    Log findById(long id);
    List<Log> findAll();
}
