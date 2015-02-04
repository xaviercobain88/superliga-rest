package generic.domain.contract;

import org.hibernate.validator.constraints.NotEmpty;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xavier on 2/3/15.
 */
@Local
public interface IImageHandler {
    String uploadTournamentImage(@NotNull InputStream imageStream, @NotEmpty String extension) throws IOException;
}
