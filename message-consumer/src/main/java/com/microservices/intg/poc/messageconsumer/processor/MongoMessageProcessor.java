package com.microservices.intg.poc.messageconsumer.processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.intg.poc.messageconsumer.domain.FxRate;
import com.microservices.intg.poc.messageconsumer.domain.FxRateDocument;
import com.microservices.intg.poc.messageconsumer.repository.FxRateDocumentRepository;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MongoMessageProcessor implements Processor {


    @Autowired
    FxRateDocumentRepository fxRateDocumentRepository;

    @Override
    public void process(Exchange exchange) throws Exception {
        String message = (String) exchange.getIn().getBody();

        FxRate fxRate = new ObjectMapper().readValue(message, FxRate.class);


//        fxRateDocumentRepository.save(new FxRateDocument(fxRate.getCurrencyPair(), message));
        fxRateDocumentRepository.save(new FxRateDocument(UUID.randomUUID().toString(), message));
    }
}
