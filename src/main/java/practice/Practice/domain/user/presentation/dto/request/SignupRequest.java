package practice.Practice.domain.user.presentation.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequest {

    @NotNull(message = "닉네임을 입력하세요")
    private String accountId;

    @NotNull(message = "이름을 입력하세요")
    private String userName;

    @NotNull(message = "이메일을 입력하세요")
    private String email;

    //@Pattern(regexp = "^(?=.*[!@#$%^&*])(?=.{1,20}$).*",
      //      message = "비밀번호는 최대 20글자이고, 특수문자 1개 이상이 포함되어야 합니다.")
    private String password;
}
