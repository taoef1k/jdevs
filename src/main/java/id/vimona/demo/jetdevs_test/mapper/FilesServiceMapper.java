package id.vimona.demo.jetdevs_test.mapper;

import id.vimona.demo.jetdevs_test.domain.Files;
import id.vimona.demo.jetdevs_test.model.FileProgress;
import id.vimona.demo.jetdevs_test.model.FilesDTO;
import id.vimona.demo.jetdevs_test.model.FilesProgressDTO;
import id.vimona.demo.jetdevs_test.model.ProgressCheckDTO;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface FilesServiceMapper {

  @Mappings({
    @Mapping(source = "files.createdDate", target = "uploadedDate")
  })
  FilesDTO mapToDTO(Files files);

  @Mappings({
    @Mapping(source = "files.checkingPercentage", target = "percentage")
  })
  FilesProgressDTO mapToProgressDTO(Files files);

  default void buildProgress(Files files, ProgressCheckDTO progressCheckDTO) {
    files.setCheckingPercentage(progressCheckDTO.getPercentage());
    if (progressCheckDTO.getPercentage() > 0 && progressCheckDTO.getPercentage() < 100) {
      files.setStatus(FileProgress.REVIEWING);
    } else {
      files.setStatus(FileProgress.COMPLETED);
    }
  }
}
