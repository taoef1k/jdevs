package id.vimona.demo.jetdevs_test.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;


@Entity
@Getter
@Setter
@SQLDelete(
  sql = "update roles set deleted_at = ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000) where role_id = ?")
@Table(name = "roles")
@Where(clause = "deleted_at = 0")
public class Roles extends Auditable<String> {

  @Id
  @Column(name = "role_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long roleId;

  @Column(nullable = false, unique = true)
  private String code;

  @Column(name = "\"description\"")
  private String description;

  @OneToMany(mappedBy = "role")
  private Set<UserRoles> userRoles;

  @OneToMany(mappedBy = "role")
  private Set<RolePermissions> rolePermissions;

}
