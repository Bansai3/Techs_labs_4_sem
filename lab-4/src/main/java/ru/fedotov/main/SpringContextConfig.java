package ru.fedotov.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@Configuration
@ComponentScan(basePackages = {
        "ru.fedotov.dao",
        "ru.fedotov.service",
        "ru.fedotov.controllers",
        "ru.fedotov.config"})
@EnableWebMvc
public class SpringContextConfig {

}
