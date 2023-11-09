package id.vimona.demo.jetdevs_test.model;

import id.vimona.demo.jetdevs_test.domain.RolePermissions;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class RolesDTO {

  private Long roleId;

  @NotNull
  @Size(max = 255)
  private String code;

  @Size(max = 255)
  private String description;

  private Set<RolePermissions> rolePermissions;

}
