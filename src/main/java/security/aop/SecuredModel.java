package security.aop;

import security.domain.enums.SecuredManageableTypeEnum;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE })
@InterceptorBinding
@Retention(RetentionPolicy.RUNTIME)

public @interface SecuredModel {
    @Nonbinding
    SecuredManageableTypeEnum[] securedManageableTypes() default SecuredManageableTypeEnum.USER;
}
