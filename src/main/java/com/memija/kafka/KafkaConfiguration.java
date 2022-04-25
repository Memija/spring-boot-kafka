package com.memija.kafka;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;

@ConditionalOnProperty(value = "${kafka_enabled}", havingValue = "true", matchIfMissing = true)
@Configuration
@EnableKafka
public class KafkaConfiguration {

    @Bean
    public TopicListLogger getTopicListLogger(ConsumerFactory<String, String> consumerFactory) {
        return new TopicListLogger(consumerFactory.createConsumer());
    }
    
}
