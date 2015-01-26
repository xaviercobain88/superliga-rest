package security.aop;


import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;


@InterceptorBinding
@Target({ElementType.METHOD, ElementType.TYPE})

@Retention(RetentionPolicy.RUNTIME)

public @interface SecuredByToken {

}
