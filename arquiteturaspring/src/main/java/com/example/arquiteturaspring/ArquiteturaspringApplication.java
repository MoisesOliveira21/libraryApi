package com.example.arquiteturaspring;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
@EnableConfigurationProperties
public class ArquiteturaspringApplication {

    public static void main(String[] args) {
        //	SpringApplication.run(ArquiteturaspringApplication.class, args);}

        SpringApplicationBuilder builder = new SpringApplicationBuilder(ArquiteturaspringApplication.class);
        builder.run(args);
        builder.bannerMode(Banner.Mode.OFF);
        builder.profiles("producao", "homolog");


        // contexto aplicação já inicializada
        ConfigurableApplicationContext applicationContext = builder.context();

//		var produtoRepository = applicationContext.getBean("produtoRepository");

        ConfigurableEnvironment environment = applicationContext.getEnvironment();

        String applicationName = environment.getProperty("spring.application.name");

        builder.lazyInitialization(true);

        // posso referenciar a classe entre aspas, entretanto letra inicial minúscula
        //applicationContext.getBean("exampleValue");
        ExampleValue value = applicationContext.getBean(ExampleValue.class);
        value.exibir();

        AppProperties properties = applicationContext.getBean(AppProperties.class);

    }
}