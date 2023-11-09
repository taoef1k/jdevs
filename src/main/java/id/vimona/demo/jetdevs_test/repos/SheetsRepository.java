package id.vimona.demo.jetdevs_test.repos;

import id.vimona.demo.jetdevs_test.domain.Sheets;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SheetsRepository extends JpaRepository<Sheets, Long> {
  void deleteByFileId(Long fileId);
}
