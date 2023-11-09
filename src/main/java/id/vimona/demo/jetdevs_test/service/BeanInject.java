package id.vimona.demo.jetdevs_test.service;

import id.vimona.demo.jetdevs_test.mapper.ExcelServiceMapper;
import id.vimona.demo.jetdevs_test.repos.ContentsRepository;
import id.vimona.demo.jetdevs_test.repos.FilesRepository;
import id.vimona.demo.jetdevs_test.repos.SheetsRepository;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class BeanInject {

  private final FilesRepository filesRepository;
  private final SheetsRepository sheetsRepository;
  private final ContentsRepository contentsRepository;
  private final ExcelServiceMapper excelServiceMapper;

  @Bean
  @Scope(BeanDefinition.SCOPE_PROTOTYPE)
  public ExcelService excelService(Workbook workbook) {
    try {
      return new ExcelService(workbook, filesRepository, sheetsRepository, contentsRepository, excelServiceMapper);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
