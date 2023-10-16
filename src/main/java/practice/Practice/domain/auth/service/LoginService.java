package practice.Practice.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.Practice.domain.user.domain.User;
import practice.Practice.domain.user.domain.repository.UserRepository;
import practice.Practice.domain.user.presentation.dto.request.LoginRequest;
import practice.Practice.domain.user.exception.PasswordMismatchException;
import practice.Practice.domain.user.exception.UserNotFoundException;
import practice.Practice.global.security.TokenResponse;
import practice.Practice.global.security.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

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
}
