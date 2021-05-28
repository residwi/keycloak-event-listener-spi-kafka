package com.github.residwi.plugin.keycloak.producer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.keycloak.events.Event;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventMessage extends Event {

    public static EventMessage create(Event event) {
        var msg = new EventMessage();
        msg.setClientId(event.getClientId());
        msg.setDetails(event.getDetails());
        msg.setError(event.getError());
        msg.setIpAddress(event.getIpAddress());
        msg.setRealmId(event.getRealmId());
        msg.setSessionId(event.getSessionId());
        msg.setTime(event.getTime());
        msg.setType(event.getType());
        msg.setUserId(event.getUserId());
        return msg;
    }
}
