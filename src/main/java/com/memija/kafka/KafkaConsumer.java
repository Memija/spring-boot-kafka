package com.memija.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${kafka_topic}" , groupId = "${kafka_group_id}")
    public void consume(ConsumerRecord<String, Object> record) {
        logIncomingMessage(record);
    }

    private void logIncomingMessage(ConsumerRecord<String, Object> record) {
        record.headers().forEach(header -> logger.info("Header {} has value of {}.", header.key(), new String(header.value(), java.nio.charset.StandardCharsets.UTF_8)));
        logger.info("Record under key {} has value of {}.", record.key(), record.value());
    }

}
