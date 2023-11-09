package id.vimona.demo.jetdevs_test.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.OffsetDateTime;
import java.util.Set;


@Entity
@Getter
@Setter
@SQLDelete(
  sql = "update permissions set deleted_at = ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000) where permission_id = ?")
@Table(name = "permissions")
@Where(clause = "deleted_at = 0")
public class Permissions extends Auditable<String> {

  @Id
  @Column(name = "permission_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long permissionId;

  @Column(nullable = false, unique = true)
  private String name;

  @OneToMany(mappedBy = "permission")
  private Set<RolePermissions> rolePermissions;

}
