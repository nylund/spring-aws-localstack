package com.example.service.rest;

import com.example.service.domain.EventMessage;
import com.example.service.sns.SnsPublisher;
import com.example.service.sqs.SqsPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    @Autowired
    SqsPublisher sqsPublisher;

    @Autowired
    SnsPublisher snsPublisher;

    @RequestMapping(
            path = "/sqs/publish",
            method = RequestMethod.GET)
    public void triggerSqsPublish(@RequestParam  String foo) {

        sqsPublisher.send(EventMessage.builder()
            .foo(foo)
            .build());

    }

    @RequestMapping(
            path = "/sns/publish",
            method = RequestMethod.GET)
    public void triggerSnsPublish(@RequestParam  String foo) {

        snsPublisher.send(EventMessage.builder()
                .foo(foo)
                .build());

    }
}
