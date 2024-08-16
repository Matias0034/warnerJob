package warner.infrastructure.repository;

import warner.domain.contract.UserContract;
import warner.domain.model.User;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends UserContract, JpaRepository<User, UUID> {
    @Query("SELECT u FROM User u WHERE u.email = :#{#user.email}")
    User findByEmail(@Param("user") User user);


    @Query("SELECT u FROM User u WHERE u.email = :#{#user.email}")
    Optional<User> verifyByEmail(@Param("user") User user);

    @Query("SELECT u FROM User u WHERE u.id = :#{#user.id} AND u.email = :#{#user.email}")
    Optional<User> verifyByIdAndEmail(@Param("user") User user);
}