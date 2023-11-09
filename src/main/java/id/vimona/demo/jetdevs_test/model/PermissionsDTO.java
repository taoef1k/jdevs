package id.vimona.demo.jetdevs_test.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PermissionsDTO {

  private Long permissionId;

  @NotNull
  @Size(max = 255)
  private String name;

}
