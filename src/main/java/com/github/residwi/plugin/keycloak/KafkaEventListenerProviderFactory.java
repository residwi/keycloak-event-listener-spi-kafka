package com.github.residwi.plugin.keycloak;

import org.keycloak.Config;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventListenerProviderFactory;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class KafkaEventListenerProviderFactory implements EventListenerProviderFactory {

    @Override
    public EventListenerProvider create(KeycloakSession keycloakSession) {
        return new KafkaEventListenerProvider();
    }

    @Override
    public void init(Config.Scope scope) {
        // default implementation ignored
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
        // default implementation ignored
    }

    @Override
    public void close() {
        // default implementation ignored
    }

    @Override
    public String getId() {
        return "keycloak-event-listener-spi-kafka";
    }
}
