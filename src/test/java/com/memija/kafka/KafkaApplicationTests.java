package com.memija.kafka;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KafkaApplicationTests {

	@Test
	void application_shouldBeAbleToStart() {
		// Arrange
		KafkaApplication kafkaApplication = new KafkaApplication();
		// Act
		String className = kafkaApplication.getClass().getName();
		// Assert
		assertEquals(className, KafkaApplication.class.toString());
	}

}
