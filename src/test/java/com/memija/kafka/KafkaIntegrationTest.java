package com.memija.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest(properties = {
    "kafka_enabled=true",
    "spring.kafka.consumer.auto-offset-reset=earliest",
    "spring.kafka.bootstrap-servers=${spring.embedded.kafka.brokers}"
})
@DirtiesContext
@EmbeddedKafka(partitions = 1)
class KafkaIntegrationTest {

    @Autowired
    private KafkaProducer kafkaProducer;

    @SpyBean
    private KafkaConsumer kafkaConsumer;

    @Test
    void testSendAndReceiveMessage() {
        String message = "Hello Kafka!";
        String topic = "my-topic";

        kafkaProducer.sendMessage(topic, message);

        ArgumentCaptor<ConsumerRecord<String, Object>> captor = ArgumentCaptor.forClass(ConsumerRecord.class);

        verify(kafkaConsumer, timeout(10000).times(1)).consume(captor.capture());

        ConsumerRecord<String, Object> consumerRecord = captor.getValue();
        assertEquals(message, consumerRecord.value());
    }
}
