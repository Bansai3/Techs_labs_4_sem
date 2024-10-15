package ru.fedotov.cats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"ru.fedotov.jpa"})
@ComponentScan({"ru.fedotov.dto", "ru.fedotov.cats"})
@EnableJpaRepositories({"ru.fedotov.jpa"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}