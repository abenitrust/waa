package com.waa.lab.repository;

import com.waa.lab.domain.Log;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogRepository extends CrudRepository<Log, Long> {
    List<Log> findAll();
}
