package practice.Practice.domain.user.service;

import org.springframework.web.multipart.MultipartFile;
import practice.Practice.domain.user.domain.User;
import practice.Practice.domain.user.service.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.Practice.infra.service.S3Util;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateImgListService {

    private final S3Util s3Util;
    private final UserFacade userFacade;

    public void imgListUpload(List<MultipartFile> multipartFiles) {

        User currentUser = userFacade.getCurrentUser();

        List<String> imgList = s3Util.uploadImages(multipartFiles);

        currentUser.imageListUpload(imgList);

    }

}
