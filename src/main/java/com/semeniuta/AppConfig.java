package com.semeniuta;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
@ComponentScan({"com.semeniuta.dao", "com.semeniuta.services", "com.semeniuta.validators", "com.semeniuta.view.impl"})
public class AppConfig {
    @Bean(name = "bufferedReader")
    public BufferedReader getBufferedReader() {

        return new BufferedReader(new InputStreamReader(System.in));
    }
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory getEntityManagerFactory() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence-unit");
        return factory;
    }


}
