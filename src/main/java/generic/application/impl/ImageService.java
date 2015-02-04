package generic.application.impl;

import com.sun.jersey.core.header.FormDataContentDisposition;
import core.application.exception.InternalServerErrorException;
import generic.application.contract.IImageService;
import generic.domain.contract.IImageHandler;
import org.hibernate.validator.constraints.NotBlank;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xavier on 2/3/15.
 */
@Stateless
public class ImageService implements IImageService {

    @Inject
    IImageHandler imageHandler;

    @Override
    public String uploadTournamentImage(@NotNull InputStream imageStream,@NotBlank String extension)
            throws InternalServerErrorException {

        String url = null;
        try {
            url = imageHandler.uploadTournamentImage(imageStream, extension);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InternalServerErrorException();
        }
        return url;
    }
}
