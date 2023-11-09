package id.vimona.demo.jetdevs_test.repos;

import id.vimona.demo.jetdevs_test.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users, Long> {

  boolean existsByUsernameIgnoreCase(String username);

  Optional<Users> findOneByUsernameIgnoreCase(String username);


}
