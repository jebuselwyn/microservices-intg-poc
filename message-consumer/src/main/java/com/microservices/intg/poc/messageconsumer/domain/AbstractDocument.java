package com.microservices.intg.poc.messageconsumer.domain;

import org.springframework.data.annotation.Id;

public class AbstractDocument {

    @Id
    private String id;
    private Object jsonMessage;


    public Object getJsonMessage() {
        return jsonMessage;
    }

    public void setJsonMessage(Object jsonMessage) {
        this.jsonMessage = jsonMessage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
