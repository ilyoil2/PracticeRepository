package practice.Practice.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import practice.Practice.domain.auth.service.LoginService;
import practice.Practice.domain.auth.service.ReissueService;
import practice.Practice.domain.auth.service.SignupService;
import practice.Practice.domain.user.presentation.dto.request.LoginRequest;
import practice.Practice.domain.user.presentation.dto.request.SignupRequest;
import practice.Practice.global.security.TokenResponse;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final SignupService signupService;
    private final LoginService loginService;
    private final ReissueService reissueService;

    @PostMapping("/signup")
    public void signup(@RequestBody @Valid SignupRequest signupRequest) {
        signupService.signUp(signupRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.login(request);
    }

    @PostMapping("/reissue")
    public TokenResponse reissue(@RequestHeader(name = "AUTHORIZATION_HEADER") String refreshToken) {
        return reissueService.reissue(refreshToken);
    }
}
