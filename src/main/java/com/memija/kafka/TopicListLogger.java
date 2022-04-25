package com.memija.kafka;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.PartitionInfo;


public class TopicListLogger {

    Consumer<String, String> consumer;

    public TopicListLogger(Consumer<String, String> consumer) {
        this.consumer = consumer;
    }

    @PostConstruct
    public void logTopics() {
        Map<String, List<PartitionInfo>> topics = consumer.listTopics();

        topics.entrySet().forEach(entry -> {
            System.out.printf("Found topic with value of {}.", entry.getKey());
        });
    }
    
}
