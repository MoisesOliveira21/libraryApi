package com.example.arquiteturaspring;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "app.config")
public class AppProperties {

    private String variable;
    private Integer value1;

    public AppProperties(String variable, Integer value1) {
        this.variable = variable;
        this.value1 = value1;
    }
}
