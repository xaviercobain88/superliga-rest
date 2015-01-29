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
    protected Long polimorphicResourceId;
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

    public Invitation(@NotNull User sender, @NotNull User recipient, InvitationTypeEnum type, Long polimorphicResourceId){
        status = InvitationStatusEnum.PENDING;
        this.sender = sender;
        this.recipient = recipient;
        this.type = type;
        this.polimorphicResourceId = polimorphicResourceId;
    }





}
