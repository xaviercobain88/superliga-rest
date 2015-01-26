package security.aop;

import api_v1.controller.BaseController;
import api_v1.controller.TokenTestController;
import api_v1.util.RestApiResponse;
import core.application.exception.InternalServerErrorException;
import core.application.exception.UnauthorizedException;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import security.application.contract.IDistributedServiceAuthenticationService;
import security.application.dto.UserDTO;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.ServletException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by xavier on 1/20/15.
 */

@SecuredByToken
@Interceptor
public class TokenGuard {


    public static final String TOKEN = "TOKEN";
    protected String token = "";
    @Inject
    protected IDistributedServiceAuthenticationService distributedServiceAuthenticationService;

    @AroundInvoke

    public Object validateToken(InvocationContext ic) throws Exception {
        BaseController baseController = (BaseController) ic.getTarget();
        baseController.getHttpRequest().getRequestURI();
        List<NameValuePair> params = null;
        try {
            params = URLEncodedUtils.parse(new URI(baseController.getHttpRequest().getQueryString() + "?" +
                    baseController.getHttpRequest().getQueryString()), "UTF-8");
            for (NameValuePair param : params) {
                if (param.getName().trim().toUpperCase().equals(TOKEN)) {
                    token = param.getValue();
                    break;
                }
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
        try {
            distributedServiceAuthenticationService.loginByToken(token);
            return ic.proceed();
        }catch (InternalServerErrorException | UnauthorizedException e){
            e.printStackTrace();
            RestApiResponse<Object> restApiResponse = new RestApiResponse<>();
            restApiResponse.addDangerMessage(e.getMessage());
            return restApiResponse;
        }


    }


}
