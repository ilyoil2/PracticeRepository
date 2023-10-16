package practice.Practice.domain.user.domain.repository;

import org.springframework.data.repository.CrudRepository;
import practice.Practice.domain.user.domain.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByAccountId(String accountId);

    Boolean existsByAccountId(String accountId);
}
