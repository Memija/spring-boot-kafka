package com.memija.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumer {
    
    @KafkaListener(topics = "${kafka_topic}" , groupId = "${kafka_group_id}")
    public void connsume(ConsumerRecord<String, Object> record) {
        logIncomingMessage(record);
    }

    private void logIncomingMessage(ConsumerRecord<String, Object> record) {
        record.headers().forEach(header -> System.out.printf("Header {} has value of {}.", header.key(), header.value()));
        System.out.printf("Record under key {} has value of {}.", record.key(), record.value());
    }

}
