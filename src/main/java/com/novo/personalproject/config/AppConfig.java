package com.novo.personalproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import org.springframework.web.filter.HiddenHttpMethodFilter;


@Configuration
@ComponentScan(basePackages = "com.novo.personalproject")
@PropertySource("classpath:application.yml")
public class AppConfig {
    @Bean
    HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}
