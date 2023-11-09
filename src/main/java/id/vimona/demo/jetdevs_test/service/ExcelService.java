package id.vimona.demo.jetdevs_test.service;

import id.vimona.demo.jetdevs_test.domain.Contents;
import id.vimona.demo.jetdevs_test.domain.Files;
import id.vimona.demo.jetdevs_test.domain.Sheets;
import id.vimona.demo.jetdevs_test.mapper.ExcelServiceMapper;
import id.vimona.demo.jetdevs_test.model.FileProgress;
import id.vimona.demo.jetdevs_test.repos.ContentsRepository;
import id.vimona.demo.jetdevs_test.repos.FilesRepository;
import id.vimona.demo.jetdevs_test.repos.SheetsRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
public class ExcelService extends AbstractImportExcel<Contents> {

  private final FilesRepository filesRepository;
  private final SheetsRepository sheetsRepository;
  private final ContentsRepository contentsRepository;
  private final ExcelServiceMapper excelServiceMapper;

  public ExcelService(Workbook workbook,
                      FilesRepository filesRepository,
                      SheetsRepository sheetsRepository,
                      ContentsRepository contentsRepository,
                      ExcelServiceMapper excelServiceMapper) throws IOException {
    super(workbook);
    this.filesRepository = filesRepository;
    this.sheetsRepository = sheetsRepository;
    this.contentsRepository = contentsRepository;
    this.excelServiceMapper = excelServiceMapper;
  }

  @Override
  protected Contents mappingCell(MappingCellParameter mappingCellParameter) {
    Contents content = new Contents();
    content.setRowNo(mappingCellParameter.getRowNumber());
    content.setFileId(mappingCellParameter.getFileId());
    content.setSheetId(mappingCellParameter.getSheetId());
    content.setHeader(mappingCellParameter.getHeaders().get(mappingCellParameter.getCellNumber()));
    switch (mappingCellParameter.getCurrentCell().getCellType()) {
      case NUMERIC -> content.setContent(String.valueOf(mappingCellParameter.getCurrentCell().getNumericCellValue()));
      case STRING -> content.setContent(mappingCellParameter.getCurrentCell().getStringCellValue());
    }

    return content;
  }

  @Transactional
  public void importDataToDB(ExcelServiceParameters excelServiceParameters) {
    try {
      // Import data from Excel file
      importData();

      // Get Excel Sheet Info
      Map<Integer, String> sheets = getSheetNames();

      // Get Header excel records
      Map<String, Map<Integer, String>> headers = getHeaders();

      // Save File Information
      Files entityFiles = excelServiceMapper.mapToFiles(excelServiceParameters.getFilename(), FileProgress.UNREVIEWED);
      Files file = filesRepository.save(entityFiles);

      // Save Records per Sheet
      sheets.forEach((no, name) -> {
        Map<Integer, String> sortedHeader = new LinkedHashMap<>();
        headers.get(name).entrySet().stream()
          .sorted(Map.Entry.comparingByKey())
          .forEachOrdered(x -> sortedHeader.put(x.getKey(), x.getValue()));
        String header = sortedHeader.values().stream().map(Object::toString).collect(Collectors.joining(","));
        Sheets sheet = sheetsRepository.save(excelServiceMapper.mapToSheets(file.getFileId(), name, header));
        List<Contents> rows = getRows(ImportExcelParameters.builder(file.getFileId(), sheet.getSheetId())
          .sheetName(name)
          .headers(headers.get(name))
          .build());

        contentsRepository.saveAll(rows);
      });
    } catch (Exception e) {
      log.error("Error on Import Data to Database >>> ", e);
      throw new RuntimeException(e);
    }
  }

  @Builder(builderMethodName = "hiddenBuilder")
  @Getter
  public static class ExcelServiceParameters {
    private InputStream inputStream;
    private String filename;

    public static ExcelServiceParametersBuilder builder(String filename) {
      return hiddenBuilder().filename(filename);
    }
  }
}
