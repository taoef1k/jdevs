package id.vimona.demo.jetdevs_test.repos;

import id.vimona.demo.jetdevs_test.domain.Contents;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ContentsRepository extends JpaRepository<Contents, Long> {
  void deleteByFileId(Long fileId);
  List<Contents> findAllByFileIdAndSheetIdOrderByRowNoAsc(Long fileId, Long sheetId);
}
