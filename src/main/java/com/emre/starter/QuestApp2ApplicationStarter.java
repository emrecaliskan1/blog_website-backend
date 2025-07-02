package com.emre.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"com.emre"})
@EntityScan(basePackages = {"com.emre"})
@ComponentScan(basePackages = {"com.emre"})
@SpringBootApplication
public class QuestApp2ApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(QuestApp2ApplicationStarter.class, args);
    }

}
