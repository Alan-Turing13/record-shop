package com.northcoders.record_shop;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ObjectMapper mapper(){
        return new ObjectMapper();
    }
}
