package core.infrastructure.impl;

import core.domain.contract.IInvitationRepository;
import core.domain.enums.InvitationStatusEnum;
import core.domain.enums.InvitationTypeEnum;
import core.domain.enums.UserStatusEnum;
import core.domain.model.Invitation;
import core.infrastructure.exception.UnexpectedPersistenceException;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.ejb.Stateless;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

@Stateless
public class InvitationRepository extends GenericRepository<Invitation, Long> implements
		IInvitationRepository{

	public InvitationRepository() {
		super(Invitation.class);
	}

	@Override
	public List<Invitation> findByResource(@Min(1) Long polymorphicResourceId,
											   @NotNull InvitationTypeEnum type, @NotEmpty List<InvitationStatusEnum> statuses)
			throws UnexpectedPersistenceException {
		String jpql = "select i from Invitation i where i.polymorphicResourceId = :polymorphicResourceId " +
				"and i.type = :type and i.status in (:statuses)";
		HashMap<String, Object> params = new HashMap<>();
		params.put("polymorphicResourceId", polymorphicResourceId);
		params.put("type", type);
		params.put("statuses", statuses);
		return findByQuery(jpql, params);
	}

	@Override
	public List<Invitation> findByResourceAndEmail(@Min(1) Long polymorphicResourceId, @NotNull InvitationTypeEnum type,
												   @NotEmpty List<InvitationStatusEnum> statuses, @Min(1) Long userId)
			throws UnexpectedPersistenceException {
		String jpql = "select i from Invitation i where i.polymorphicResourceId = :polymorphicResourceId " +
				"and i.type = :type and i.status in :statuses and i.recipient.id =:userId";
		HashMap<String, Object> params = new HashMap<>();
		params.put("polymorphicResourceId", polymorphicResourceId);
		params.put("type", type);
		params.put("statuses", statuses);
		params.put("userId", userId);
		return findByQuery(jpql, params);
	}
}
