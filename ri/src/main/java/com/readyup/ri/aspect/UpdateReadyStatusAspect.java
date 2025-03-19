package com.readyup.ri.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UpdateReadyStatusAspect {

    @Pointcut("@annotation(UpdateReadyStatus)")
    public void updateReadyStatusPointcut() {}

    @After("updateReadyStatusPointcut()")
    public void update(ProceedingJoinPoint joinPoint) throws Throwable {
        String hugl = null;
    }
}
