package com.github.residwi.plugin.keycloak;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.OperationType;
import org.keycloak.events.admin.ResourceType;

public class KafkaEventListenerProvider implements EventListenerProvider {

    @Override
    public void onEvent(Event event) {
        publishEvent("registerEvent", EventType.REGISTER, event);
        publishEvent("loginEvent", EventType.LOGIN, event);
        publishEvent("verifyEmailEvent", EventType.VERIFY_EMAIL, event);
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        publishAdminEvent("deleteUserAdminEvent", OperationType.DELETE, ResourceType.USER, adminEvent);
    }

    @Override
    public void close() {
        // default implementation ignored
    }

    private void publishEvent(String topic, EventType eventType, Event event) {
        if (event.getType().equals(eventType)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                KafkaMessageProducer.publishEvent(topic, objectMapper.writeValueAsString(event));
            } catch (JsonProcessingException exception) {
                exception.printStackTrace();
            }
        }
    }

    private void publishAdminEvent(String topic, OperationType operationType, ResourceType resourceType, AdminEvent adminEvent) {
        if (adminEvent.getOperationType().equals(operationType) && adminEvent.getResourceType().equals(resourceType)) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                KafkaMessageProducer.publishEvent(topic, objectMapper.writeValueAsString(adminEvent));
            } catch (JsonProcessingException exception) {
                exception.printStackTrace();
            }
        }
    }
}
