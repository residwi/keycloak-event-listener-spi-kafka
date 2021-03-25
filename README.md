# Keycloak Event Listener SPI & Publish events to Kafka

## Setup

Kafka running at port 9092 or update in KafkaMessageProducer.java

```java
private static final String KAFKA_SERVER = "127.0.0.1:9092";
```

## Build

```bash
$ mvn clean package
```

## Deploy to Keycloak

```bash
$ cp keycloak-event-listener-spi-kafka.jar /keycloak-x.x.x/standalone/deployments
```

Restart the keycloak server

```bash
$ ./bin/standalone.sh
```
  