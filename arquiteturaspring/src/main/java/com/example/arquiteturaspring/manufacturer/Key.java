package com.example.arquiteturaspring.manufacturer;

public class Key {
    private ManuFacturer manuFacturer;
    private String type;

    public ManuFacturer getManuFacturer() {
        return manuFacturer;
    }

    public void setManuFacturer(ManuFacturer manuFacturer) {
        this.manuFacturer = manuFacturer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
