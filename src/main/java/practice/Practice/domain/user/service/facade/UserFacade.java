package practice.Practice.domain.user.service.facade;

import practice.Practice.domain.user.domain.User;
import practice.Practice.domain.user.domain.UserRepository;
import practice.Practice.domain.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;
    public User getCurrentUser() {
        String nickName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByNickName(nickName)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);

    }

}