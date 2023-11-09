package id.vimona.demo.jetdevs_test.repos;

import id.vimona.demo.jetdevs_test.domain.Permissions;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

  boolean existsByNameIgnoreCase(String name);

}
