package com.github.residwi.plugin.keycloak.producer;

public interface Producer {

    void sendOnTopic(String topic);
}
