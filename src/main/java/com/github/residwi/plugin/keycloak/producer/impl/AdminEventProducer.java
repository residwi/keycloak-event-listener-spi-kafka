package com.github.residwi.plugin.keycloak.producer.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.residwi.plugin.keycloak.producer.util.KafkaProducerUtil;
import com.github.residwi.plugin.keycloak.producer.Producer;
import org.keycloak.events.admin.AdminEvent;

public class AdminEventProducer implements Producer {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final AdminEvent event;

    public AdminEventProducer(AdminEvent event) {
        this.event = event;
    }

    @Override
    public void sendOnTopic(String topic) {
        try {
            KafkaProducerUtil.publishEvent(topic, objectMapper.writeValueAsString(event));
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }
    }
}
