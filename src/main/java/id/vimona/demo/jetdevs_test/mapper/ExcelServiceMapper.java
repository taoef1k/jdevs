package id.vimona.demo.jetdevs_test.mapper;

import id.vimona.demo.jetdevs_test.domain.Files;
import id.vimona.demo.jetdevs_test.domain.Sheets;
import id.vimona.demo.jetdevs_test.model.FileProgress;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ExcelServiceMapper {

  @Mappings({
    @Mapping(source = "name", target = "name"),
    @Mapping(source = "fileId", target = "fileId"),
    @Mapping(source = "header", target = "header")
  })
  Sheets mapToSheets(Long fileId, String name, String header);

  @Mappings({
    @Mapping(source = "filename", target = "name"),
    @Mapping(source = "progress", target = "status")
  })
  Files mapToFiles(String filename, FileProgress progress);
}
