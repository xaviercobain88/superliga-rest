package core.domain.model;

import core.domain.enums.InvitationStatusEnum;
import core.domain.enums.InvitationTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by xavier on 1/28/15.
 */
@Entity
public class Invitation {
    @Id
    @GeneratedValue
    protected Long id;
    protected Long polymorphicResourceId;
    @Enumerated(EnumType.STRING)
    protected InvitationTypeEnum type;
    @Enumerated(EnumType.STRING)
    protected InvitationStatusEnum status;
    @ManyToOne
    @JoinColumn(name = "sender_user_id")
    protected User sender;
    @ManyToOne
    @JoinColumn(name = "recipient_user_id")
    protected User recipient;

    public Invitation(@NotNull User sender, @NotNull User recipient, InvitationTypeEnum type, Long polymorphicResourceId) {
        status = InvitationStatusEnum.PENDING;
        this.sender = sender;
        this.recipient = recipient;
        this.type = type;
        this.polymorphicResourceId = polymorphicResourceId;
    }

    public Invitation() {
    }

    public boolean isValidStatus(InvitationStatusEnum status) {
        if (status != null && this.status != null && status.equals(this.status)) {

            return true;
        }
        return false;
    }

    public boolean isValidType(InvitationTypeEnum type) {
        if (type != null && this.type != null && type.equals(this.type)) {

            return true;
        }
        return false;
    }

    public void acceptInvitation(){
        status = InvitationStatusEnum.ACCEPTED;
    }
    public void refuseInvitation(){
        status = InvitationStatusEnum.REFUSED;
    }

    public Long getPolymorphicResourceId() {
        return polymorphicResourceId;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }
}
