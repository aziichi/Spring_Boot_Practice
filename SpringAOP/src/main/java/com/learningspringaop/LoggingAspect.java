package com.learningspringaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.learningspringaop.ShoppingCart.checkout(..))")
    public void beforeLogger(JoinPoint jp){
        System.out.println("Show checkout status: "+ jp.getArgs()[0]);
    }

    @After("execution(* *.*.*.checkout(..))")
    public void afterLogger(){
        System.out.println("After Logger");
    }

    @Pointcut("execution(* com.learningspringaop.ShoppingCart.quantity())")
    public void afterReturningPointCut(){}

    @AfterReturning(pointcut = "afterReturningPointCut()", returning = "retVal")
    public void afterReturning(int retVal){
        System.out.println("Returning value: "+ retVal);
    }
}
