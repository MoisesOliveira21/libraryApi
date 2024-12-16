package com.example.arquiteturaspring.manufacturer.configuration;


import com.example.arquiteturaspring.manufacturer.Engine;
import com.example.arquiteturaspring.manufacturer.EngineType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ManufacturerConfiguration {

    @Bean(name = "engineAspirated")
    public Engine createAspiratedEngine() {
        var engine = new Engine();
        engine.setHorsepower(120);
        engine.setCylinders(4);
        engine.setModel("TXA");
        engine.setEngine(EngineType.ASPIRATED);
        return engine;
    }

    @Bean(name = "engineElectric")
    public Engine createElectricEngine() {
        var engine = new Engine();
        engine.setHorsepower(150);
        engine.setCylinders(6);
        engine.setModel("BHN");
        engine.setEngine(EngineType.ELECTRIC);
        return engine;
    }

    @Bean(name = "engineTurbo")
    @Primary
    public Engine createTurboEngine() {
        var engine = new Engine();
        engine.setHorsepower(130);
        engine.setCylinders(10);
        engine.setModel("MVQ");
        engine.setEngine(EngineType.TURBO);
        return engine;
    }
}