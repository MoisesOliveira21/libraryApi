package com.example.arquiteturaspring.manufacturer;

public class Engine {

    private String model;
    private int horsepower;
    private int cylinders;
    private EngineType engine;




    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public int getCylinders() {
        return cylinders;
    }

    public void setCylinders(int cylinders) {
        this.cylinders = cylinders;
    }

    public EngineType getEngine() {
        return engine;
    }

    public void setEngine(EngineType engine) {
        this.engine = engine;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Engine{");
        sb.append("model='").append(model).append('\'');
        sb.append(", horsepower=").append(horsepower);
        sb.append(", cylinders=").append(cylinders);
        sb.append(", engine=").append(engine);
        sb.append('}');
        return sb.toString();
    }
}
