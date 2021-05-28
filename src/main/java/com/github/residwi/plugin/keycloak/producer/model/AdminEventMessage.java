package com.github.residwi.plugin.keycloak.producer.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;
import org.keycloak.events.admin.AdminEvent;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminEventMessage extends AdminEvent {

    @JsonRawValue
    private String representation;

    public static AdminEventMessage create(AdminEvent adminEvent) {
        var msg = new AdminEventMessage();
        msg.setAuthDetails(adminEvent.getAuthDetails());
        msg.setError(adminEvent.getError());
        msg.setOperationType(adminEvent.getOperationType());
        msg.setRealmId(adminEvent.getRealmId());
        msg.setRepresentation(adminEvent.getRepresentation());
        msg.setResourcePath(adminEvent.getResourcePath());
        msg.setResourceType(adminEvent.getResourceType());
        msg.setResourceTypeAsString(adminEvent.getResourceTypeAsString());
        msg.setTime(adminEvent.getTime());
        return msg;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }

    @Override
    public void setRepresentation(String representation) {
        this.representation = representation;
    }
}
