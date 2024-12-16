package com.example.arquiteturaspring.todos;


import org.springframework.stereotype.Component;

@Component
public class MailSender {
    public void enviar(String message){
        System.out.println("Email enviado");
    }

}
