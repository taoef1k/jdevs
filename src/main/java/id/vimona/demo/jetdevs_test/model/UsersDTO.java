package id.vimona.demo.jetdevs_test.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UsersDTO {

  private Long userId;

  @NotNull
  @Size(max = 150)
  private String username;

  @NotNull
  @Size(max = 255)
  private String password;

  @NotNull
  @Size(max = 255)
  private String name;

  @NotNull
  @Size(max = 255)
  private String email;

}
