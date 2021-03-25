package com.github.residwi.plugin.keycloak;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;

public class KafkaEventListenerProvider implements EventListenerProvider {

    @Override
    public void onEvent(Event event) {
        if (event.getType().equals(EventType.LOGIN)) {
            KafkaMessageProducer.publishEvent("loginEvent", event.getUserId());
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
