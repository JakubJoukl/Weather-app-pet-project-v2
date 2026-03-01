package com.example.weatherapppetprojectv2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut(value = "execution(* com.example.weatherapppetprojectv2..*(..))")
    public void anyMethodPointcut() {

    }

    @Pointcut(value = "within(com.example.weatherapppetprojectv2.exceptionHandlers..*)")
    public void globalExceptionHandlersPointcut() {

    }

    //Asi pada v JwtAuthFilter
    @Pointcut(value = "within(com.example.weatherapppetprojectv2.config..*)")
    public void configPointcut() {

    }

    //TODO fix - handluje i ty v global exception handlers - chyby totiz vznikaji na kontrolleru
    @AfterThrowing(
            pointcut = "anyMethodPointcut() && !globalExceptionHandlersPointcut() && !configPointcut()",
            throwing = "ex"
    )
    public void logExceptions(JoinPoint joinPoint, Throwable ex) {
        //TODO něco lepšího než reflexe?
        if(ex.getClass().getPackageName().equals("com.example.weatherapppetprojectv2.exception")) {
            return;
        }

        log.debug("Exception in method: {}.{}() with parameters: {}",
                 joinPoint.getSignature().getDeclaringTypeName(),
                 joinPoint.getSignature().getName(),
                 joinPoint.getArgs(),
                 ex);
    }
}
