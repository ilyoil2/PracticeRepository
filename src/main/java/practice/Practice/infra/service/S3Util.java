package practice.Practice.infra.service;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import practice.Practice.infra.exception.ImageUploadFailedException;
import practice.Practice.infra.exception.WrongImageException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class S3Util {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    private final AmazonS3 amazonS3;

    @Transactional
    public String uploadImage (MultipartFile image) {
        if (image.isEmpty() || image.getOriginalFilename() == null) {
            throw ImageUploadFailedException.EXCEPTION;
        }

        String fileName = UUID.randomUUID() + image.getOriginalFilename();

        try {
            PutObjectRequest request = new PutObjectRequest(
                    bucket, fileName, image.getInputStream(), getObjectMetadata(image)
            ).withCannedAcl(CannedAccessControlList.PublicRead);

            amazonS3.putObject(request);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return getFileUrl(fileName);
    }

    @Transactional
    public List<String> uploadImages(List<MultipartFile> multipartFile) {

        List<String> imgUrlList = new ArrayList<>();

        for (MultipartFile file : multipartFile) {
            String fileName = createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try(InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket+"/post/image", fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
                imgUrlList.add(amazonS3.getUrl(bucket+"/post/image", fileName).toString());
            } catch(IOException e) {
                throw ImageUploadFailedException.EXCEPTION;
            }
        }
        return imgUrlList;
    }

    private ObjectMetadata getObjectMetadata(MultipartFile image) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(image.getSize());
        objectMetadata.setContentType(image.getContentType());

        return objectMetadata;
    }

    private String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    private String getFileExtension(String fileName) {
        if (fileName.length() == 0) {
            throw WrongImageException.EXCEPTION;
        }
        ArrayList<String> fileValidate = new ArrayList<>();
        fileValidate.add(".jpg");
        fileValidate.add(".jpeg");
        fileValidate.add(".png");
        fileValidate.add(".JPG");
        fileValidate.add(".JPEG");
        fileValidate.add(".PNG");
        String idxFileName = fileName.substring(fileName.lastIndexOf("."));
        if (!fileValidate.contains(idxFileName)) {
            throw WrongImageException.EXCEPTION;
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public void deleteFile(String fileName) throws IOException{
        try {
            amazonS3.deleteObject(bucket, fileName);
        } catch (SdkClientException e){
            throw new IOException("삭제를 실패하였습니다.", e);
        }

    }

    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }
}