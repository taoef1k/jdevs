package id.vimona.demo.jetdevs_test.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;


@Getter
@Setter
public class FilesProgressDTO {

  private Long fileId;

  @Size(max = 150)
  private String name;

  private String status;

  private String percentage;

  public String getPercentage() {
    if (Objects.isNull(percentage)) {
      percentage = "0%";
    } else {
      percentage += "%";
    }
    return percentage;
  }
}
