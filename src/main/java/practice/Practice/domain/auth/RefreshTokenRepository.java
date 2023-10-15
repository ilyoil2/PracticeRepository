package practice.Practice.domain.auth;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RefreshTokenRepository extends JpaRepository<RefreshToken,String> {

    Optional<RefreshToken> findByNickName(String nickName);

    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
