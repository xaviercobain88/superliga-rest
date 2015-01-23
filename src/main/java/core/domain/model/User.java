package core.domain.model;

import core.application.validator.ValidPassword;
import core.domain.enums.SecuredManageableTypeEnum;
import core.domain.enums.UserRegistrationStatusEnum;
import core.domain.contract.SecuredManageable;
import core.domain.validator.DTOValidationGroup;
import core.domain.validator.OnSaveValidationGroup;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@ValidPassword(groups = DTOValidationGroup.class, message = "{errors.validation.valid_password.user}")
public class User {


    @Id
    @GeneratedValue
    protected Long id;
    @NotNull(message = "{errors.validation.not_null.user.username}")
    protected String username;
    @NotNull(message = "{errors.validation.not_null.user.first_name}")
    protected String firstName;
    @NotNull(message = "{errors.validation.not_null.user.last_name}")
    protected String lastName;
    @NotNull(groups = OnSaveValidationGroup.class)
    protected String password;
    protected String token;
    @Enumerated(EnumType.STRING)
    protected UserRegistrationStatusEnum registrationStatus;
    @ManyToAny(
            metaColumn = @Column(name = "secure_manageable_type")
    )
    @AnyMetaDef(
            idType = "integer",
            metaType = "string",
            metaValues = {
                    @MetaValue(
                            value = "TOURNAMENT", targetEntity = Tournament.class
                    ),
                    @MetaValue(
                            value = "PLAYER", targetEntity = Player.class
                    )
            }

    )
    @JoinTable(name = "user_secure_manageable", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "secure_manageable_id"))
    protected List<SecuredManageable> securedManageables;


    public void addSecureManageable(SecuredManageable securedManageable) {
        if (securedManageable == null) {
            return;
        }
        if (securedManageables == null) {
            securedManageables = new ArrayList<>();
        }
        securedManageables.add(securedManageable);

    }

    public boolean isManager(Long manageableResourceId, SecuredManageableTypeEnum securedManageableType) {

        if (securedManageables != null) {
            for (SecuredManageable securedManageable : securedManageables) {
                if (securedManageable.getId().equals(manageableResourceId) &&
                        securedManageableType.equals(securedManageable.getSecuredManageableType())) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean matchedPassword(String comparePassword){
        if(comparePassword!=null&&password!=null){
            return comparePassword.equals(password);
        }
        return false;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
