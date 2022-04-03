package com.waa.lab.aop;

import com.waa.lab.domain.Log;
import com.waa.lab.helper.Factory;
import com.waa.lab.service.ILogService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalTime;

@Aspect
@Configuration
public class LoggerAop {

    private ILogService logService;

    @Autowired
    public LoggerAop(ILogService logService) {
        this.logService = logService;
    }

    @Pointcut("@annotation(com.waa.lab.annotation.ExecutionTime)")
    public void executionTime() {}

    @Around("executionTime()")
    public Object calculateExecutionTime(ProceedingJoinPoint pjp) throws  Throwable{
        Log log = new Log();
        log.setDate(LocalDate.now());
        log.setOperation(pjp.getSignature().getName());

        long start = System.currentTimeMillis();
        Object returnVal = pjp.proceed();
        long end = System.currentTimeMillis();
        long duration = end - start;
        log.setTime(duration);

        logService.save(log);

        return returnVal;
    }

}
