package practice.Practice.global.security.auth;


import practice.Practice.domain.user.domain.User;
import practice.Practice.domain.user.domain.repository.UserRepository;
import practice.Practice.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String accountId) {

        User user = userRepository.findByAccountId(accountId)
                .orElseThrow(()-> UserNotFoundException.EXCEPTION);

        return new AuthDetails(user.getAccountId());

     }
}
