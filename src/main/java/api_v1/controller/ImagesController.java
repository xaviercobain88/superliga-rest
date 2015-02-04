package api_v1.controller;

import api_v1.util.BaseApiResponse;
import api_v1.util.RestApiResponse;
import core.application.exception.InternalServerErrorException;
import generic.application.contract.IImageService;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import security.aop.SecuredByToken;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by xavier on 1/24/15.
 */
@Path("/images")
@RequestScoped
public class ImagesController extends BaseController {


    @Inject
    protected IImageService imageService;
    @Inject
    protected HttpServletRequest httpServletRequest;

    @POST
    @SecuredByToken
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("multipart/form-data")
    public BaseApiResponse uploadTournamentImage(MultipartFormDataInput input) {


        RestApiResponse<String> restApiResponse = new RestApiResponse<>();

        String fileName = "";

        Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
        List<InputPart> inputParts = uploadForm.get("file");

        if (inputParts != null && !inputParts.isEmpty()) {
            InputPart inputPart = inputParts.get(0);

            try {

                MultivaluedMap<String, String> header = inputPart.getHeaders();
                fileName = getFileName(header);

                //convert the uploaded file to inputstream
                InputStream inputStream = inputPart.getBody(InputStream.class, null);


                //constructs upload file path
                restApiResponse.setData(imageService.uploadTournamentImage(inputStream, fileName));

            } catch (IOException | InternalServerErrorException e) {
                e.printStackTrace();
                restApiResponse.addDangerMessage(e.getMessage());
            }
        }


        return restApiResponse;

    }

    //get uploaded filename, is there a easy way in RESTEasy?
    private String getFileName(MultivaluedMap<String, String> header) {

        String[] contentDisposition = header.getFirst("Content-Disposition").split(";");

        for (String filename : contentDisposition) {
            if ((filename.trim().startsWith("filename"))) {

                String[] name = filename.split("=");

                String finalFileName = name[1].trim().replaceAll("\"", "");
                return finalFileName;
            }
        }
        return "unknown";
    }

    //save to somewhere
    private void writeFile(byte[] content, String filename) throws IOException {

        File file = new File(filename);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fop = new FileOutputStream(file);

        fop.write(content);
        fop.flush();
        fop.close();

    }
}



