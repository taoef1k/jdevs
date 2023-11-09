package id.vimona.demo.jetdevs_test.rest;

import id.vimona.demo.jetdevs_test.model.DetailContentDTO;
import id.vimona.demo.jetdevs_test.model.FilesDTO;
import id.vimona.demo.jetdevs_test.model.FilesProgressDTO;
import id.vimona.demo.jetdevs_test.model.ProgressCheckDTO;
import id.vimona.demo.jetdevs_test.service.FilesService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/files", produces = MediaType.APPLICATION_JSON_VALUE)
public class FilesResource {

  private final FilesService filesService;

  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  @GetMapping("/uploaded")
  public ResponseEntity<List<FilesDTO>> getAllFiless() {
    return ResponseEntity.ok(filesService.findAll());
  }

  @Secured("ROLE_ADMIN")
  @DeleteMapping("/{fileId}")
  @ApiResponse(responseCode = "204")
  public ResponseEntity<Void> deleteFiles(@PathVariable(name = "fileId") final Long fileId) {
    filesService.delete(fileId);
    return ResponseEntity.noContent().build();
  }

  @Secured({"ROLE_ADMIN", "ROLE_USER"})
  @GetMapping("/{fileId}/review")
  public ResponseEntity<DetailContentDTO> getDetailContent(@PathVariable(name = "fileId") final Long fileId) {
    return ResponseEntity.ok(filesService.getDetailContent(fileId));
  }

  @Secured("ROLE_ADMIN")
  @PutMapping("/{fileId}/check")
  public ResponseEntity<String> fileCheck(@PathVariable(name = "fileId") final Long fileId,
                                          @Valid @RequestBody ProgressCheckDTO progressCheckDTO) {
    filesService.updateProgressCheck(fileId, progressCheckDTO);
    return ResponseEntity.ok("Successfully update progress checking file.");
  }

  @Secured("ROLE_ADMIN")
  @GetMapping("/progress")
  public ResponseEntity<List<FilesProgressDTO>> progress() {
    return ResponseEntity.ok(filesService.findAllProgress());
  }

}
