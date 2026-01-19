package com.memija.kafka;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ConditionalOnProperty(name = "kafka_enabled", havingValue = "true", matchIfMissing = true)
public class KafkaController {

    private final KafkaProducer kafkaProducer;

    public KafkaController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/publish")
    public void publishMessage(@RequestBody String message, @RequestParam(required = false) String topic) {
        if (topic != null) {
            kafkaProducer.sendMessage(topic, message);
        } else {
            kafkaProducer.sendMessage(message);
        }
    }
}
