package id.vimona.demo.jetdevs_test.repos;

import id.vimona.demo.jetdevs_test.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface FilesRepository extends JpaRepository<Files, Long> {
  @Query("FROM Files f " +
    "JOIN FETCH f.sheets fs " +
    "WHERE f.fileId = :fileId")
  Optional<Files> findJoinSheetsByFileId(Long fileId);
}
