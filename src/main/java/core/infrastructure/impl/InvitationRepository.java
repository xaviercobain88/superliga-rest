package core.infrastructure.impl;

import core.domain.contract.IInvitationRepository;
import core.domain.model.Invitation;

import javax.ejb.Stateless;

@Stateless
public class InvitationRepository extends GenericRepository<Invitation, Long> implements
		IInvitationRepository{

	public InvitationRepository() {
		super(Invitation.class);
	}

}
