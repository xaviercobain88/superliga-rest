package api_v1.controller;

import api_v1.util.RestApiMapResponse;
import api_v1.util.RestApiResponse;
import core.domain.enums.DisciplineEnum;
import core.domain.enums.PlayingModeEnum;
import core.domain.model.DisciplineSettings;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Created by xavier on 1/24/15.
 */
@Path("/disciplines")
@RequestScoped
public class DisciplinesController extends BaseController {

    protected RestApiMapResponse<DisciplineEnum, ArrayList<PlayingModeEnum>> restApiMapResponse;

    public DisciplinesController() {
        restApiMapResponse = new RestApiMapResponse<>();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)


    public RestApiMapResponse<DisciplineEnum, ArrayList<PlayingModeEnum>> login() {
        restApiMapResponse.setData(DisciplineSettings.settings);
        return restApiMapResponse;

    }
}
