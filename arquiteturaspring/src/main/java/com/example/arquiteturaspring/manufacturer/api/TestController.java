package com.example.arquiteturaspring.manufacturer.api;

import com.example.arquiteturaspring.manufacturer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/car")
public class TestController {


        @Autowired
        //@Qualifier("engineAspirated")
        @EngineAspirated
        private Engine engine;

    @PostMapping
    public CarStatus startCar(@RequestBody Key key) {
        var car = new HondaAtw(engine);
       return car.startEngine(key);

    }
}
