package com.microservices.intg.poc.messageconsumer.config;


import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoConfig {

    @Bean(name = "mongoBean")
    public MongoClient mongoClient(@Value("${spring.data.mongodb.host}")String hostname,
                                   @Value("${spring.data.mongodb.port}")Integer port){

        return new MongoClient(hostname, port);

    }

}
