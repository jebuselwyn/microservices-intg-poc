package com.microservices.intg.poc.messageconsumer.config;

import com.microservices.intg.poc.messageconsumer.processor.MongoMessageProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CamelRouteBuilder extends RouteBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(CamelRouteBuilder.class);

    @Autowired
    MongoMessageProcessor mongoMessageProcessor;

    @Override
    public void configure() throws Exception {

        PropertiesComponent pc = getContext().getComponent("properties", PropertiesComponent.class);
        pc.setLocation("classpath:application.properties");

        log.info("About to start route: Camel Consumer -> Log ");


        from("kafka:{{topic.fxRate}}?brokers={{kafka.host}}:{{kafka.port}}"
                + "&maxPollRecords={{consumer.maxPollRecords}}"
                + "&consumersCount={{consumer.consumersCount}}"
                + "&seekTo={{consumer.seekTo}}"
                + "&groupId={{consumer.group}}")
                .routeId("FromFxRateTopic")
                .choice().when(body().isNotNull())
                .log("${body}")
                .process(mongoMessageProcessor)
                .endChoice()
        ;
    }
}
