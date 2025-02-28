package pl.ps.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ps.model.entity.UserEntity;

/**
 * @author Piotr Skowron
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	UserEntity findByLogin(String login);
}
