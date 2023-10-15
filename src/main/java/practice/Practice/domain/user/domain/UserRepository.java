package practice.Practice.domain.user.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByNickName(String nickName);

    Boolean existsByNickName(String nickName);
}
