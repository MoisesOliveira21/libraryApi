package com.example.arquiteturaspring.manufacturer;

import java.awt.*;

public class HondaAtw extends Car {
    public HondaAtw(Engine engine) {
        super(engine);
        setModel("Atw");
        setColor(Color.BLACK);
        setManuFacturer(ManuFacturer.HONDA);
    }
}
