package com.github.residwi.plugin.keycloak;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.events.Event;

public class EventProducer {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Event event;

    public EventProducer(Event event) {
        this.event = event;
    }

    public void sendOnTopic(String topic) {
        try {
            KafkaProducerUtil.publishEvent(topic, objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }
    }
}
