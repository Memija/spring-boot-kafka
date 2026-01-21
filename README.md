[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Memija_spring-boot-kafka&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Memija_spring-boot-kafka)

# Spring Boot Kafka Demo

This project is a sample Spring Boot application demonstrating integration with Apache Kafka. It provides functionalities to publish messages to Kafka topics via a REST API and consume messages from a configured topic.

## Features

- **Message Producer**: Publish messages to any Kafka topic using a REST endpoint.
- **Message Consumer**: Listen to a configured Kafka topic and log incoming messages along with their headers.
- **Topic Discovery**: Automatically lists and logs all available Kafka topics upon application startup.
- **Conditional Execution**: Kafka components can be enabled or disabled via configuration properties.

## Prerequisites

- Java 11 or higher
- Maven
- A running Apache Kafka instance

## Configuration

The application is configured via `src/main/resources/application.yaml`. Key properties include:

| Property | Description | Default Value |
| --- | --- | --- |
| `spring.kafka.bootstrap-servers` | Kafka broker address | `kafka:9092` |
| `kafka_topic` | Default topic for producing and consuming | `my-topic` |
| `kafka_group_id` | Consumer group ID | `my-group` |
| `kafka_enabled` | Enable or disable Kafka features | `true` |

You can override these properties using environment variables or command-line arguments. For example, to set the bootstrap servers:
`SPRING_KAFKA_BOOTSTRAP_SERVERS=localhost:9092 ./mvnw spring-boot:run`

## Getting Started

### 1. Start Kafka
Ensure you have a reachable Kafka broker. If you don't have one, you can run one using Docker or follow the [Apache Kafka Quickstart](https://kafka.apache.org/quickstart).

### 2. Build the Project
```bash
./mvnw clean install
```

### 3. Run the Application
```bash
./mvnw spring-boot:run
```

## Usage

### Publish a Message
You can send a message to a Kafka topic using the `/publish` endpoint.

**Endpoint:** `POST /publish`

**Parameters:**
- `topic` (optional): The target Kafka topic. If omitted, the value of `kafka_topic` property is used.
- **Body**: The message content (text/plain).

**Example using cURL:**

Send to default topic:
```bash
curl -X POST -H "Content-Type: text/plain" -d "Hello, Kafka!" http://localhost:8080/publish
```

Send to a specific topic (`test-topic`):
```bash
curl -X POST -H "Content-Type: text/plain" -d "Hello, Custom Topic!" "http://localhost:8080/publish?topic=test-topic"
```

### Consume Messages
The application automatically listens to the topic specified in `kafka_topic`. When a message is received, it logs the headers and the payload to the console:

```
INFO ... KafkaConsumer : Header {header_key} has value of {header_value}.
INFO ... KafkaConsumer : Record under key {key} has value of {message_payload}.
```

### List Topics
On startup, the application logs all topics available in the Kafka cluster:

```
INFO ... TopicListLogger : Found topic with value of {topic_name}.
```
