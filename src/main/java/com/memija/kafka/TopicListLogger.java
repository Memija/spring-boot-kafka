package com.memija.kafka;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.PartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TopicListLogger {

    private final Logger logger = LoggerFactory.getLogger(TopicListLogger.class);
    Consumer<String, String> consumer;

    public TopicListLogger(Consumer<String, String> consumer) {
        this.consumer = consumer;
    }

    @PostConstruct
    public void logTopics() {
        try {
            Map<String, List<PartitionInfo>> topics = consumer.listTopics();

            topics.entrySet().forEach(entry -> {
                logger.info("Found topic with value of {}.", entry.getKey());
            });
        } catch (Exception e) {
            logger.error("Failed to list topics", e);
        }
    }

    @PreDestroy
    public void closeConsumer() {
        if (consumer != null) {
            consumer.close();
        }
    }
    
}
