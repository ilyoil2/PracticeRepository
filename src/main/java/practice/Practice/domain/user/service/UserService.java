package practice.Practice.domain.user.service;

import practice.Practice.domain.auth.RefreshTokenRepository;
import practice.Practice.domain.user.presentation.dto.request.LoginRequest;
import practice.Practice.domain.user.presentation.dto.request.SignupRequest;
import practice.Practice.domain.user.presentation.dto.response.MyPageResponse;
import practice.Practice.domain.user.domain.User;
import practice.Practice.domain.user.domain.repository.UserRepository;
import practice.Practice.domain.user.service.exception.PasswordMismatchException;
import practice.Practice.domain.user.service.exception.UserAlreadyExistException;
import practice.Practice.domain.user.service.exception.UserNotFoundException;
import practice.Practice.domain.user.service.facade.UserFacade;
import practice.Practice.global.security.TokenResponse;
import practice.Practice.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserFacade userFacade;

    @Transactional
    public void signUp(SignupRequest signupRequest) {

        if (userRepository.existsByAccountId(signupRequest.getAccountId())) {
            throw UserAlreadyExistException.EXCEPTION;
        }

        userRepository.save(
                User.builder()
                        .email(signupRequest.getEmail())
                        .userName(signupRequest.getUserName())
                        .accountId(signupRequest.getAccountId())
                        .password(signupRequest.getPassword())
                        .build()
        );
    }

    @Transactional
    public TokenResponse login(LoginRequest request) {

        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

        if (!request.getPassword().equals(user.getPassword())) {
            throw PasswordMismatchException.EXCEPTION;
        }

        TokenResponse token = jwtTokenProvider.createToken(user.getAccountId());
        return token;

    }

    @Transactional
    public TokenResponse reissue(String refreshToken) {
        return jwtTokenProvider.reissue(refreshToken);
    }

    public MyPageResponse myPage() {
        User currentUser = userFacade.getCurrentUser();
        return new MyPageResponse(currentUser);
    }
}
