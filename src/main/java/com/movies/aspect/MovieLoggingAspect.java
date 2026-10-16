package com.movies.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MovieLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.movies.controller.MovieController.*(..))")
    public void movieControllerMethods() {
    }

    @Before("movieControllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("==> Entering Method: {} with arguments: {}",
                joinPoint.getSignature().getName(),
                joinPoint.getArgs());
    }

//    @AfterReturning(pointcut = "movieControllerMethods()", returning = "result")
//    public void logAfter(JoinPoint joinPoint, Object result) {
//        logger.info("<== Method {} finished. Returned: {}",
//                joinPoint.getSignature().getName(),
//                result);
//    }
}
