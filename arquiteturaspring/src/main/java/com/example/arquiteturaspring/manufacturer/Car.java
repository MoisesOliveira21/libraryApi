package com.example.arquiteturaspring.manufacturer;

import java.awt.*;

public class Car {
    private String model;
    private Color color;
    private Engine engine;
    private ManuFacturer manuFacturer;


    public Car(Engine engine) {
       this.engine = engine;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public ManuFacturer getManuFacturer() {
        return manuFacturer;
    }

    public void setManuFacturer(ManuFacturer manuFacturer) {
        this.manuFacturer = manuFacturer;
    }


    public CarStatus startEngine(Key key) {
        if (key.getManuFacturer() != this.manuFacturer) {
            return new CarStatus("Não é possível iniciar o carro com esta chave");
        }
        return new CarStatus("Carro está ligando, rodando o motor com " + engine);
    }
}
