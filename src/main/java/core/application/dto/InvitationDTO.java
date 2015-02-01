package core.application.dto;

import core.domain.enums.InvitationStatusEnum;
import core.domain.enums.InvitationTypeEnum;
import core.domain.model.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by xavier on 1/28/15.
 */
public class InvitationDTO {
    protected Long id;
    protected Long polymorphicResourceId;
    protected InvitationTypeEnum type;
    protected InvitationStatusEnum status;
    protected Long senderId;
    protected Long recipientId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPolymorphicResourceId() {
        return polymorphicResourceId;
    }

    public void setPolymorphicResourceId(Long polymorphicResourceId) {
        this.polymorphicResourceId = polymorphicResourceId;
    }

    public InvitationTypeEnum getType() {
        return type;
    }

    public void setType(InvitationTypeEnum type) {
        this.type = type;
    }

    public InvitationStatusEnum getStatus() {
        return status;
    }

    public void setStatus(InvitationStatusEnum status) {
        this.status = status;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
    }
}
