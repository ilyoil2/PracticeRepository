package practice.Practice.global.security.auth;


import practice.Practice.domain.user.domain.User;
import practice.Practice.domain.user.domain.UserRepository;
import practice.Practice.domain.user.service.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nickName) {

        User user = userRepository.findByNickName(nickName)//그냥 유저 없으면 오류 발생
                .orElseThrow(UserNotFoundException::new);

        return new AuthDetails(user.getNickName());//아니면

     }
}
