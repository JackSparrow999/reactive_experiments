package com.ron99.ReactiveExperiments.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/sqs")
public class SqsController {

    @Autowired
    private QueueMessagingTemplate queueMessagingTemplate;

    @Value("${cloud.aws.end-point.uri}")
    String queueUrl;

    @GetMapping("/send")
    public Mono<String> sendStringToQueue(@RequestParam("message") String payload){
        queueMessagingTemplate.send(queueUrl, MessageBuilder.withPayload(payload).build());
        return Mono.just("Message sent to queue!");
    }

}
