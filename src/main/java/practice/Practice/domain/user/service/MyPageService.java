package practice.Practice.domain.user.service;

import practice.Practice.domain.user.presentation.dto.response.MyPageResponse;
import practice.Practice.domain.user.domain.User;
import practice.Practice.domain.user.service.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {

    private final UserFacade userFacade;

    public MyPageResponse myPage() {
        User currentUser = userFacade.getCurrentUser();
        return new MyPageResponse(currentUser);
    }
}
