package id.vimona.demo.jetdevs_test.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgressCheckDTO {
  @NotNull(message = "percentage value required.")
  @Min(message = "Minimum value is 0", value = 0)
  @Max(message = "Maximum value is 100", value = 100)
  private int percentage;
}
