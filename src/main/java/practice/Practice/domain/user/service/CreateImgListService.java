package practice.Practice.domain.user.service;

import practice.Practice.domain.user.domain.User;
import practice.Practice.domain.user.service.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateImgListService {

    private final UserFacade userFacade;

    public void imgListUpload(List<String> imgPaths) {
        User user = userFacade.getCurrentUser();

        User currentUser = userFacade.getCurrentUser();

        user.imageListUpload(imgPaths);


    }

}
