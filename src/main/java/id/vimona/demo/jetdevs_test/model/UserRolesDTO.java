package id.vimona.demo.jetdevs_test.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRolesDTO {

  private Long userRoleId;

  @NotNull
  private Long userId;

  @NotNull
  private Long roleId;

  private Long user;

  private Long role;

}
