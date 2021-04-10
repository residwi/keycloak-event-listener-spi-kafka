package com.github.residwi.plugin.keycloak;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;

public class KafkaEventListenerProvider implements EventListenerProvider {

    @Override
    public void onEvent(Event event) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (event.getType().equals(EventType.LOGIN)) {
            try {
                KafkaMessageProducer.publishEvent("loginEvent", objectMapper.writeValueAsString(event));
            } catch (JsonProcessingException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        // default implementation ignored
    }

    @Override
    public void close() {
        // default implementation ignored
    }
}
