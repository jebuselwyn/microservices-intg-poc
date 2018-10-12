package com.microservices.intg.poc.messageconsumer.domain;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class FxRateDocument extends AbstractDocument {

    private FxRate fxRateObj;

    public FxRateDocument(String id, String message) {
        setId(id);
        setJsonMessage(message);
        try {
            setFxRateObj(new ObjectMapper().readValue(message, FxRate.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFxRateObj(FxRate fxRateObj) {
        this.fxRateObj = fxRateObj;
    }
}
