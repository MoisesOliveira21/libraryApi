package com.example.modulo1.model;

import org.springframework.beans.factory.annotation.Autowired;

public class Cliente {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
