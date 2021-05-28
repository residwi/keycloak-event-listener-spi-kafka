package com.github.residwi.plugin.keycloak;

import com.github.residwi.plugin.keycloak.producer.impl.AdminEventProducer;
import com.github.residwi.plugin.keycloak.producer.impl.EventProducer;
import com.github.residwi.plugin.keycloak.producer.model.AdminEventMessage;
import com.github.residwi.plugin.keycloak.producer.model.EventMessage;
import com.github.residwi.plugin.keycloak.producer.Producer;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.events.admin.ResourceType;

public class KafkaEventListenerProvider implements EventListenerProvider {

    @Override
    public void onEvent(Event event) {
        EventMessage message = EventMessage.create(event);
        Producer producer = new EventProducer(message);

        if (event.getType().equals(EventType.REGISTER)) {
            producer.sendOnTopic("registerEvent");
        } else if (event.getType().equals(EventType.VERIFY_EMAIL)) {
            producer.sendOnTopic("verifyEmailEvent");
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
        AdminEventMessage message = AdminEventMessage.create(adminEvent);
        Producer producer = new AdminEventProducer(message);

        if (adminEvent.getResourceType().equals(ResourceType.USER)) {
            producer.sendOnTopic("userKeycloakEvent");
        }
    }

    @Override
    public void close() {
        // default implementation ignored
    }
}
