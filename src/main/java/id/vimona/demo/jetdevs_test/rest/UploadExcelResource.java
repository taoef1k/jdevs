package id.vimona.demo.jetdevs_test.rest;

import id.vimona.demo.jetdevs_test.service.ExcelService;
import id.vimona.demo.jetdevs_test.util.ExcelHelper;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Slf4j
@RestController
@RequestMapping(value = "/api/upload")
public class UploadExcelResource {

  @Lookup
  public ExcelService excelService(Workbook workbook) {
    return null;
  }

  @Secured("ROLE_ADMIN")
  @PostMapping(consumes = {"multipart/form-data"})
  @ApiResponse(responseCode = "201")
  public ResponseEntity<String> uploadExcel(@RequestParam("file") MultipartFile file) {
    StringBuilder message = new StringBuilder();
    if (ExcelHelper.hasExcelFormat(file)) {
      try (InputStream is = file.getInputStream()) {
        Workbook workbook = ExcelHelper.isOlderVersion(file) ? new HSSFWorkbook(is) : new XSSFWorkbook(is);
        excelService(workbook).importDataToDB(ExcelService.ExcelServiceParameters
          .builder(file.getOriginalFilename())
            .inputStream(file.getInputStream())
          .build());
        workbook.close();
        message.append("Uploaded the file successfully: ").append(file.getOriginalFilename());
        return ResponseEntity.status(HttpStatus.OK).body(message.toString());
      } catch (Exception e) {
        log.error("Error when process upload file >>>>", e);
        message.append("Could not upload the file: ").append(file.getOriginalFilename()).append("!");
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message.toString());
      }
    }

    message.append("Please upload an excel file!");
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message.toString());
  }
}
