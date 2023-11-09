package id.vimona.demo.jetdevs_test.repos;

import id.vimona.demo.jetdevs_test.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RolesRepository extends JpaRepository<Roles, Long> {

  boolean existsByCodeIgnoreCase(String code);

}
