package com.logistic.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@ComponentScan({"com.logistic.task.config",
        "com.logistic.task.controller",
"com.logistic.task.service",
"com.logistic.task.mapper"})
@EntityScan("com.logistic.task.entity")
@EnableJpaRepositories("com.logistic.task.repository")
public class LogisticApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticApplication.class, args);

    }

}
