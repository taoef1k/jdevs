package id.vimona.demo.jetdevs_test.repos;

import id.vimona.demo.jetdevs_test.domain.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

  @Query("FROM UserRoles ur " +
    "JOIN FETCH ur.role urr " +
    "JOIN FETCH urr.rolePermissions urp " +
    "JOIN FETCH urp.permission up " +
    "WHERE ur.userId = :userId")
  List<UserRoles> findRoleByUserId(Long userId);
}
