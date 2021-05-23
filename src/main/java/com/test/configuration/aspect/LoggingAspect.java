package com.test.configuration.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * @author rajeshramawat
 * @version 1.0.0
 */
@Aspect
@Configuration
@Slf4j
public class LoggingAspect {

    @Autowired
    private ObjectMapper objectMapper;

    @Pointcut("execution(* com.test.controller.impl.*.*(..))")
    public void loggerForControllers() {
        //This is a pointcut which is used in AOP logger function, that's why is is empty.
    }

    @Before("loggerForControllers()")
    public void logBefore(JoinPoint joinPoint) {

        log.debug("\n-----------------------------------------------------\n");
        try {
            log.debug("entering controller method - {}, args {}", joinPoint.getTarget().getClass()+"#"+joinPoint.getSignature(), objectMapper.writeValueAsString(joinPoint.getArgs()));
        } catch (JsonProcessingException jpe) {
            log.debug("entering controller method - {}", joinPoint.getTarget().getClass()+"#"+joinPoint.getSignature());
        }
    }

    @After("loggerForControllers()")
    public void logAfter(JoinPoint joinPoint) {
        try {
            log.debug("returning point controller method - {}, args {}", joinPoint.getTarget().getClass()+"#"+joinPoint.getSignature(), objectMapper.writeValueAsString(joinPoint.getArgs()));
        } catch (JsonProcessingException jpe) {
            log.debug("returning point controller method - {}", joinPoint.getTarget().getClass()+"#"+joinPoint.getSignature());
        }
        log.debug("\n-----------------------------------------------------\n");
    }

}
