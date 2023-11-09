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
  sql = "update users set deleted_at = ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000) where user_id = ?")
@Table(name = "users")
@Where(clause = "deleted_at = 0")
public class Users extends Auditable<String> {

  @Id
  @Column(name = "user_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(nullable = false, unique = true, length = 150)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @OneToMany(mappedBy = "user")
  private Set<UserRoles> userRoles;

}
