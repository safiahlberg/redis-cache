package com.wixia.domain.repositorydelay;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * This is to simulate delays in the repository layer.
 */
@Aspect
@Configuration
public class RepositoryDelayAspect {

    private final Logger logger = LoggerFactory.getLogger(RepositoryDelayAspect.class);

    @Pointcut("within(com.wixia.domain.*Repository+) && execution(* findById(..))")
    public void repositoryMethods() {
        // Pointcuts for the repository findById methods, we leave the rest of the methods unaffected otherwise
        // startup sequence will be affected
    }

    @Before("repositoryMethods()")
    public void before(JoinPoint joinPoint) {
        logger.info("Enter {}.{}, Delaying {}ms",
            joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName(), 7000);

        delay(7000);
    }

    @After("repositoryMethods()")
    public void after(JoinPoint joinPoint) {
        logger.info("Exit {}.{}",
            joinPoint.getSignature().getDeclaringTypeName(),
            joinPoint.getSignature().getName());
    }

    private void delay(long delayInMs) {
        if (delayInMs > 0) {
            try {
                Thread.sleep(delayInMs);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
