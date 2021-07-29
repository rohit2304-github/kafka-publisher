package com.assignment.spring.kafka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;        
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publish")
public class Controller {

    @Autowired
    private KafkaTemplate<String, Driver> objectTemplate;

    @Value("${message.topic.name}")
    private String topic;

    @Value("${driver.topic.name}")
    private String driverTopicName;

    @PostMapping("/driver-location")
    public String publishDriverLocation(@RequestBody Driver driver) {
       // objectTemplate.send(driverTopicName, driver);
        ListenableFuture<SendResult<String, Driver>> listenableFuture = objectTemplate.send(driverTopicName, driver);

        listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, Driver>>() {

            @Override
            public void onSuccess(SendResult<String, Driver> result) {
                System.out.println("SUCCESS: Sent message=[" + driver.toString() + "] with offset=[" + result.getRecordMetadata()
                        .offset() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("FAILED: message=[" + driver.toString() + "] due to : " + ex.getMessage());
            }
        });
        return "Driver data published";
    }

}
