package id.vimona.demo.jetdevs_test.util;

import org.springframework.web.multipart.MultipartFile;

public class ExcelHelper {
  public static String TYPE_XLSX = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
  public static String TYPE_XLS = "application/vnd.ms-excel";

  public static boolean hasExcelFormat(MultipartFile file) {
    return TYPE_XLSX.equals(file.getContentType()) || TYPE_XLS.equals(file.getContentType());
  }

  public static boolean isOlderVersion(MultipartFile file) {
    return TYPE_XLS.equals(file.getContentType());
  }
}
