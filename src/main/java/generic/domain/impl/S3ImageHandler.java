package generic.domain.impl;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.util.IOUtils;
import generic.domain.contract.IImageHandler;
import generic.domain.enums.ImageTypeEnum;
import org.hibernate.validator.constraints.NotEmpty;
import utils.RandomNamesGenerator;

import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import java.io.*;

/**
 * Created by xavier on 2/3/15.
 */
@Stateless
public class S3ImageHandler implements IImageHandler {
    public static final String AWS_ACCESS_KEY_ID = "AKIAIKNWUNN7TJ4DTY2Q";
    public static final String AWS_SECRET_KEY = "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";
    public static final String BUCKET = "superliga-bucket";
    public static final String DOMAIN = "http://s3-us-west-2.amazonaws.com/";

    @Override
    public String uploadTournamentImage(@NotNull InputStream imageStream, @NotEmpty String extension) throws IOException {
        return uploadImage(imageStream, extension, ImageTypeEnum.TOURNAMENT);
    }

    private String uploadImage(@NotNull InputStream imageStream, @NotEmpty String extension, ImageTypeEnum imageTypeEnum) throws IOException {
        AWSCredentials credentials = new BasicAWSCredentials(AWS_ACCESS_KEY_ID, AWS_SECRET_KEY);
        AmazonS3 conn = new AmazonS3Client(credentials);

        byte[] image = IOUtils.toByteArray(imageStream);
        ByteArrayInputStream input = new ByteArrayInputStream(image);
        String name = imageTypeEnum.name() + "/" + RandomNamesGenerator.randomIdentifier() + "." + extension;
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType("image/jpeg");
        PutObjectResult putObjectResult = conn.putObject(
                new PutObjectRequest(BUCKET, name, input, new ObjectMetadata())
                        .withCannedAcl(CannedAccessControlList.PublicRead));

        return DOMAIN + BUCKET + "/" + name;
    }
}
