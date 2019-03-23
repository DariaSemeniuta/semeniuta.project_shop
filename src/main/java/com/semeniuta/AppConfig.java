package com.semeniuta;

import com.semeniuta.dao.ClientDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
@ComponentScan({"com.semeniuta.dao", "com.semeniuta.services", "com.semeniuta.validators", "com.semeniuta.view.impl"})
public class AppConfig {
    @Bean(name = "bufferedReade")
    public BufferedReader getBufferedReader() {

        return new BufferedReader(new InputStreamReader(System.in));
    }

}
