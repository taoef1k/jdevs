package id.vimona.demo.jetdevs_test.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RolePermissionsDTO {

  private Long id;

  @NotNull
  private Long roleId;

  @NotNull
  private Long permissionId;

  private Long role;

  private Long permission;

}
