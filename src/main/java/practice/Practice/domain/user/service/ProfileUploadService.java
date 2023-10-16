package practice.Practice.domain.user.service;

import practice.Practice.domain.user.domain.User;
import practice.Practice.domain.user.service.facade.UserFacade;
import practice.Practice.infra.service.S3Util;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ProfileUploadService {

    private final UserFacade userFacade;
    private final S3Util s3Facade;

    @Transactional
    public void profileUpload(MultipartFile profileImage) throws IOException {
        User user = userFacade.getCurrentUser();

        String profileImageUrl = "";

        if(user.getProfileImageUrl() != null && !user.getProfileImageUrl().isEmpty()){
            profileImageUrl = user.getProfileImageUrl();
            s3Facade.deleteFile(profileImageUrl.split("/")[3]);
        }

        profileImageUrl = s3Facade.uploadImage(profileImage);

        user.profileUpload(profileImageUrl);

    }

}