package com.microservices.intg.poc.messageproducer.controller;

import com.microservices.intg.poc.messageproducer.helper.KafkaMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/message")
public class MessageController {

    @Autowired
    KafkaMessageSender kafkaMessageSender;


    @RequestMapping(path = {"/"}, method = RequestMethod.GET)
    public String info() {

        return "PRODUCER";
    }

    @RequestMapping(path = {"/{topicName}"}, method = RequestMethod.POST)
    public ResponseEntity postFxRate(@PathVariable("topicName") String topicName,
                                     @RequestBody String message) {

        kafkaMessageSender.sendMessage(topicName, message);

        return ResponseEntity.ok().build();
    }

}
