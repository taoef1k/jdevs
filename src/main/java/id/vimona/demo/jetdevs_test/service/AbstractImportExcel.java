package id.vimona.demo.jetdevs_test.service;

import lombok.Builder;
import lombok.Getter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

abstract class AbstractImportExcel<T> {
  private final Workbook workbook;
  private Map<Integer, String> sheets = new HashMap<>();
  private Map<String, Map<Integer, String>> headers = new HashMap<>();
  private List<T> rows = new ArrayList<>();

  public AbstractImportExcel(Workbook workbook) {
    this.workbook = workbook; //new HSSFWorkbook(inputStream);
  }

  protected abstract T mappingCell(MappingCellParameter mappingCellParameter);

  protected Map<Integer, String> getSheetNames() {
    return this.sheets;
  }

  protected Map<String, Map<Integer, String>> getHeaders() {
    return this.headers;
  }

  protected List<T> getRows(ImportExcelParameters importExcelParameters) {
    generateRows(importExcelParameters);
    return this.rows;
  }

  protected void importData() {
    AtomicInteger sheetNumber = new AtomicInteger(0);

    workbook.forEach(sheet -> {
      sheets.put(sheetNumber.getAndIncrement(), sheet.getSheetName());

      for (Row currentRow : sheet) {
        if (currentRow.getRowNum() == 0) {
          headers.put(sheet.getSheetName(), this.generateHeaders(currentRow));
          break;
        }
      }
    });
  }

  private Map<Integer, String> generateHeaders(Row currentRow) {
    Map<Integer, String> headers = new HashMap<>();
    Iterator<Cell> cellsInRow = currentRow.iterator();
    int columnNo = 0;

    while (cellsInRow.hasNext()) {
      Cell currentCell = cellsInRow.next();
      headers.put(columnNo, currentCell.getStringCellValue());
      columnNo++;
    }

    return headers;
  }

  private void generateRows(ImportExcelParameters importExcelParameters) {
    workbook.forEach(sheet -> {
      if (sheet.getSheetName().equals(importExcelParameters.getSheetName())) {
        for (Row currentRow : sheet) {
          if (currentRow.getRowNum() == 0) {
            continue;
          }

          Iterator<Cell> cellsInRow = currentRow.iterator();

          int cellIdx = 0;
          while (cellsInRow.hasNext()) {
            Cell currentCell = cellsInRow.next();
            rows.add(mappingCell(MappingCellParameter.builder()
              .rowNumber(currentRow.getRowNum())
              .cellNumber(cellIdx)
              .fileId(importExcelParameters.getFileId())
              .sheetId(importExcelParameters.getSheetId())
              .headers(importExcelParameters.getHeaders())
              .currentCell(currentCell)
              .build()));

            cellIdx++;
          }
        }
      }
    });
  }

  @Builder(builderMethodName = "hiddenBuilder")
  @Getter
  public static class ImportExcelParameters {
    private Long fileId;
    private Long sheetId;
    private String sheetName;
    private Map<Integer, String> headers;

    public static ImportExcelParametersBuilder builder(Long fileId, Long sheetId) {
      return hiddenBuilder().fileId(fileId).sheetId(sheetId);
    }
  }

  @Builder
  @Getter
  public static class ResponseExcel<T> {
    private String filename;
    private Map<Integer, String> headers;
    private List<T> rowContents;
  }

  @Builder
  @Getter
  public static class MappingCellParameter {
    private int rowNumber;
    private int cellNumber;
    private long fileId;
    private long sheetId;
    private Map<Integer, String> headers;
    private Cell currentCell;
  }
}
