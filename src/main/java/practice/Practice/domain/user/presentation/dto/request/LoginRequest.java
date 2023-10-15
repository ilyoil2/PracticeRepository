package practice.Practice.domain.user.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    @NotNull(message = "닉네임을 입력하세요")
    private String nickName;

    @NotNull(message = "비밀번호를 입력하세요")
    private String password;
}
