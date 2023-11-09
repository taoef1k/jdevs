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
  sql = "update role_permissions set deleted_at = ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000) where id = ?")
@Table(name = "role_permissions")
@Where(clause = "deleted_at = 0")
public class RolePermissions extends Auditable<String> {

  @Id
  @Column(name = "id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "role_id", nullable = false)
  private Long roleId;

  @Column(name = "permission_id", nullable = false)
  private Long permissionId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false, insertable = false, updatable = false)
  private Roles role;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "permission_id", referencedColumnName = "permission_id", nullable = false, insertable = false, updatable = false)
  private Permissions permission;

}
