package id.vimona.demo.jetdevs_test.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class DetailContentDTO {
  private String filename;
  private List<Sheet> sheets;
  @JsonFormat(pattern = "dd MMMM yyyy HH:mm", timezone = "Asia/Bangkok")
  private LocalDateTime lastAccessDate;
  private String lastReviewedBy;

  @Builder
  @Getter
  public static class Sheet {
    private String name;
    private String header;
    private List<String> content;
  }

}
