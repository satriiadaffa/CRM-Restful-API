package satriiadaffa.crm.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import satriiadaffa.crm.api.models.UserModel;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,Long> {
    Optional<UserModel> findByUsername(String Username);

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
