package ru.fedotov.dao.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("ru.fedotov.dao.entity")
@EnableJpaRepositories(basePackages = "ru.fedotov.dao.dao")
public class PersistenceSpringContextConfig {
}