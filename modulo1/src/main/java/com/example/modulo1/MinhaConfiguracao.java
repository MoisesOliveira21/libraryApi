package com.example.modulo1;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Development
public class MinhaConfiguracao {
    @Bean
    public CommandLineRunner executar() {
        return arg -> {
            System.out.println("Rodando config de desenvolvimento");
        };
    }
}
