package security.domain.impl;

import core.application.exception.BadRequestException;
import core.domain.contract.IInvitationRepository;
import core.domain.contract.IUserRepository;
import core.domain.enums.UserStatusEnum;
import core.domain.exception.DomainModelNotLoadedException;
import core.domain.model.Invitation;
import core.domain.model.User;
import core.infrastructure.exception.UnexpectedPersistenceException;
import security.domain.contract.IAuthenticationHandler;
import security.domain.contract.IInvitationAuthorizationHandler;
import security.domain.enums.InvitationSourceEnum;
import utils.exception.InvalidArgumentException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xavier on 1/23/15.
 */
@Stateless
public class AuthenticationHandler implements IAuthenticationHandler, IInvitationAuthorizationHandler {
    @Inject
    protected IUserRepository userRepository;
    @Inject
    protected IInvitationRepository invitationRepository;

    @Override
    public User login(String username, String password) throws InvalidArgumentException, UnexpectedPersistenceException, DomainModelNotLoadedException, NoSuchAlgorithmException {
        if (username == null || username.length() < 1 || password == null || password.length() < 1) {
            throw new InvalidArgumentException("Incorrect username or password");
        }

        User user = userRepository.findByUsername(username, UserStatusEnum.ACTIVE);
        MessageDigest mdEnc = MessageDigest.getInstance("MD5");
        mdEnc.update(password.getBytes(), 0, password.length());
        String hashedPassword = new BigInteger(1, mdEnc.digest()).toString(16); // Hash value
        if (user.matchedPassword(hashedPassword)) {
            return user;
        }
        throw new InvalidArgumentException("Incorrect username or password");
    }

    @Override
    public boolean isAllowed(@Min(1) Long invitationId, @Min(1) Long userId, @NotNull InvitationSourceEnum source)
            throws UnexpectedPersistenceException, DomainModelNotLoadedException {
        Invitation invitation = invitationRepository.load(invitationId);
        if (invitation != null) {
            return (invitation.getRecipient() != null && invitation.getRecipient().getId().equals(userId)) &&
                    (source.equals(InvitationSourceEnum.BOTH) || source.equals(InvitationSourceEnum.RECIPIENT)) ||
                    (invitation.getSender() != null && invitation.getSender().getId().equals(userId) &&
                            (source.equals(InvitationSourceEnum.BOTH) || source.equals(InvitationSourceEnum.SENDER)));
        } else {
            throw new DomainModelNotLoadedException("Invitation not found");
        }
    }
}
