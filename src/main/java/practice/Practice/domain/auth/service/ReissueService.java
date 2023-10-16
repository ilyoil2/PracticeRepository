package practice.Practice.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.Practice.domain.user.domain.repository.UserRepository;
import practice.Practice.global.security.TokenResponse;
import practice.Practice.global.security.jwt.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class ReissueService {

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse reissue(String refreshToken) {
        return jwtTokenProvider.reissue(refreshToken);
    }
}
