package com.learningspringaop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.learningspringaop")
@EnableAspectJAutoProxy
public class BeanConfig {

}
