package com.learningspringaop;

import org.springframework.stereotype.Component;

@Component
public class ShoppingCart {
    public void checkout(String status){
        System.out.println("Checkout method is called.");
    }

    public int quantity(){
        return 2;
    }
}
