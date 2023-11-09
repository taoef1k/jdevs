package id.vimona.demo.jetdevs_test.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Getter
@Setter
@SQLDelete(
  sql = "update user_roles set deleted_at = ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000) where user_role_id = ?")
@Table(name = "user_roles")
@Where(clause = "deleted_at = 0")
public class UserRoles extends Auditable<String> {

  @Id
  @Column(name = "user_role_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userRoleId;

  @Column(name = "user_id", nullable = false)
  private Long userId;

  @Column(name = "role_id", nullable = false)
  private Long roleId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
  private Users user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, insertable = false, updatable = false)
  private Roles role;

}
