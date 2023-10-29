package practice.Practice.domain.user.presentation.dto.response;

import practice.Practice.domain.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageResponse {

    private String userName;
    private String profileImageUrl;

    public MyPageResponse(User user) {
        userName = user.getUsername();
        profileImageUrl = user.getProfileImageUrl();
    }

}
