package com.github.residwi.plugin.keycloak;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jboss.logging.Logger;

import java.util.Properties;

public class KafkaMessageProducer {

    private static final Logger LOG = Logger.getLogger(KafkaMessageProducer.class);
    private static final String KAFKA_SERVER = "kafka.kafka.svc.cluster.local:9092";

    private KafkaMessageProducer() {
    }

    public static void publishEvent(String topic, String value) {
        resetThreadContext();

        Producer<String, String> producer = new KafkaProducer<>(getProperties());
        producer.send(new ProducerRecord<>(topic, value), (recordMetadata, e) -> {
            if (e != null) {
                LOG.error("Error! Failed send event");
                e.printStackTrace();
            } else {
                LOG.infof("Success! Event topic %s has been sent: %s", recordMetadata.topic(), value);
            }
        });

        producer.flush();

        producer.close();
    }

    public static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_SERVER);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        return properties;
    }

    private static void resetThreadContext() {
        Thread.currentThread().setContextClassLoader(null);
    }
}
