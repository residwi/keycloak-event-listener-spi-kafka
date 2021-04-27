package com.github.residwi.plugin.keycloak;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.events.admin.AdminEvent;

public class KafkaEventListenerProvider implements EventListenerProvider {

    @Override
    public void onEvent(Event event) {
        EventProducer eventProducer = new EventProducer(event);

        if (event.getType().equals(EventType.REGISTER)) {
            eventProducer.sendOnTopic("registerEvent");
            eventProducer.sendOnTopic("verifyEmailEvent"); /* user signup from identity provider */
        } else if (event.getType().equals(EventType.VERIFY_EMAIL)) {
            eventProducer.sendOnTopic("verifyEmailEvent"); /* user signup from manual/form register */
        }
    }

    @Override
    public void onEvent(AdminEvent adminEvent, boolean b) {
//        AdminEventProducer eventProducer = new AdminEventProducer(adminEvent);
//
//        if (adminEvent.getOperationType().equals(OperationType.DELETE) && adminEvent.getResourceType().equals(ResourceType.USER)) {
//            eventProducer.sendOnTopic("deleteUserAdminEvent");
//        }
    }

    @Override
    public void close() {
        // default implementation ignored
    }
}
