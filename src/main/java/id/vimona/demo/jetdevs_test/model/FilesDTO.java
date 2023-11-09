package id.vimona.demo.jetdevs_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
public class FilesDTO {

  private Long fileId;

  @Size(max = 150)
  private String name;

  @JsonFormat(pattern = "dd MMMM yyyy HH:mm", timezone = "Asia/Bangkok")
  private LocalDateTime uploadedDate;
}
