package com.example.arquiteturaspring;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExampleValue {


    @Value("${app.config.variable}")
    private String variable;

   void exibir(){
        System.out.println(variable);
    }
}
