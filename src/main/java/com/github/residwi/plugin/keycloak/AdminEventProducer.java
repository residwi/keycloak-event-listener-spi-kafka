package com.github.residwi.plugin.keycloak;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.events.admin.AdminEvent;

public class AdminEventProducer {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final AdminEvent event;

    public AdminEventProducer(AdminEvent event) {
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
