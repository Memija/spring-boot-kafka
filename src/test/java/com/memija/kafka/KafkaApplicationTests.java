package com.memija.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {"kafka_enabled=false", "spring.kafka.listener.auto-startup=false", "spring.kafka.bootstrap-servers=localhost:9092"})
class KafkaApplicationTests {

	@Test
	void contextLoads() {
	}

}
