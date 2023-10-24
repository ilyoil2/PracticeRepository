package practice.Practice.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.Practice.domain.user.domain.User;
import practice.Practice.domain.user.domain.repository.UserRepository;
import practice.Practice.domain.user.presentation.dto.request.SignupRequest;
import practice.Practice.domain.user.exception.UserAlreadyExistException;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    public void signUp(SignupRequest signupRequest) {

        if (userRepository.existsByAccountId(signupRequest.getAccountId())) {
            throw UserAlreadyExistException.EXCEPTION;
        }

        String password = passwordEncoder.encode(signupRequest.getPassword());

        userRepository.save(
                User.builder()
                        .email(signupRequest.getEmail())
                        .username(signupRequest.getUserName())
                        .accountId(signupRequest.getAccountId())
                        .password(password)
                        .build()
        );
    }
}
