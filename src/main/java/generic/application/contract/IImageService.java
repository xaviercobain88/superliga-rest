package generic.application.contract;

import com.sun.jersey.core.header.FormDataContentDisposition;
import core.application.exception.InternalServerErrorException;
import org.hibernate.validator.constraints.NotBlank;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;
import java.io.InputStream;

/**
 * Created by xavier on 2/3/15.
 */
@Local
public interface IImageService {
    String uploadTournamentImage(@NotNull InputStream imageStream,@NotBlank String extension) throws InternalServerErrorException;

}
