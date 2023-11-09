package id.vimona.demo.jetdevs_test.service;

import id.vimona.demo.jetdevs_test.domain.Contents;
import id.vimona.demo.jetdevs_test.domain.Files;
import id.vimona.demo.jetdevs_test.mapper.FilesServiceMapper;
import id.vimona.demo.jetdevs_test.model.*;
import id.vimona.demo.jetdevs_test.repos.ContentsRepository;
import id.vimona.demo.jetdevs_test.repos.FilesRepository;
import id.vimona.demo.jetdevs_test.repos.SheetsRepository;
import id.vimona.demo.jetdevs_test.util.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class FilesService {

  private final FilesRepository filesRepository;
  private final FilesServiceMapper filesServiceMapper;
  private final ContentsRepository contentsRepository;
  private final SheetsRepository sheetsRepository;

  public List<FilesDTO> findAll() {
    final List<Files> fileses = filesRepository.findAll(Sort.by("fileId"));
    return fileses.stream()
      .map(files -> filesServiceMapper.mapToDTO(files))
      .toList();
  }

  @Transactional
  public void delete(final Long fileId) {
    if (!filesRepository.existsById(fileId)) {
      throw new NotFoundException(String.format("Record with ID [%d] not exist.", fileId));
    }
    contentsRepository.deleteByFileId(fileId);
    sheetsRepository.deleteByFileId(fileId);
    filesRepository.deleteById(fileId);
  }

  public DetailContentDTO getDetailContent(Long fileId) {
    if (!filesRepository.existsById(fileId)) {
      throw new NotFoundException(String.format("Record with ID [%d] not exist.", fileId));
    }
    Files files = filesRepository.findJoinSheetsByFileId(fileId).orElseThrow(NotFoundException::new);
    DetailContentDTO.DetailContentDTOBuilder result = DetailContentDTO.builder();
    result.filename(files.getName());
    List<DetailContentDTO.Sheet> sheetsDto = new ArrayList<>();
    files.getSheets().stream().forEach(sheets -> {
      DetailContentDTO.Sheet sheetDto = DetailContentDTO.Sheet.builder()
        .name(sheets.getName())
        .header(sheets.getHeader())
        .content(this.contentGenerator(fileId, sheets.getSheetId()))
        .build();
      sheetsDto.add(sheetDto);
    });
    result.sheets(sheetsDto);
    if (!FileProgress.UNREVIEWED.equals(files.getStatus())) {
      result.lastAccessDate(files.getLastModifiedDate());
      result.lastReviewedBy(files.getLastModifiedBy());
    }

    return result.build();
  }

  private List<String> contentGenerator(final Long fileId, final Long sheetId) {
    List<String> result = new ArrayList<>();
    List<Contents> contents = contentsRepository.findAllByFileIdAndSheetIdOrderByRowNoAsc(fileId, sheetId);
    if (CollectionUtils.isEmpty(contents)) {
      return result;
    }
    contents.stream()
      .collect(Collectors.groupingBy(Contents::getRowNo))
      .forEach((rowNo, body) -> {
        StringBuilder builder = new StringBuilder();
        builder.append("" + rowNo).append(". ");
        String headerBody = body
          .stream()
          .map(a -> a.getHeader() + "= " + a.getContent())
          .collect(Collectors.joining(","));
        builder.append(headerBody);
        result.add(builder.toString());
      });

    return result;
  }

  @Transactional
  public void updateProgressCheck(final Long fileId, ProgressCheckDTO progressCheckDTO) {
    Files files = filesRepository.findById(fileId)
      .orElseThrow(NotFoundException::new);
    filesServiceMapper.buildProgress(files, progressCheckDTO);
    filesRepository.save(files);
  }

  public List<FilesProgressDTO> findAllProgress() {
    final List<Files> files = filesRepository.findAll(Sort.by("fileId"));
    return files.stream()
      .map(file -> filesServiceMapper.mapToProgressDTO(file))
      .toList();
  }
}
