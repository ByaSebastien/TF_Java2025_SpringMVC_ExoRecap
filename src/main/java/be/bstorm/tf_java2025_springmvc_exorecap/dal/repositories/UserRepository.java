package be.bstorm.tf_java2025_springmvc_exorecap.dal.repositories;

import be.bstorm.tf_java2025_springmvc_exorecap.dl.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
