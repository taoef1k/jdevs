package id.vimona.demo.jetdevs_test.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;


@Entity
@Getter
@Setter
@SQLDelete(
  sql = "update contents set deleted_at = ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000) where content_id = ?")
@Table(name = "contents")
@Where(clause = "deleted_at = 0")
public class Contents extends Auditable<String> {

  @Id
  @Column(name = "content_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long contentId;

  @Column(name = "file_id", nullable = false)
  private Long fileId;

  @Column(name = "sheet_id", nullable = false)
  private Long sheetId;

  @Column(length = 200, nullable = false)
  private String header;

  @Column(columnDefinition = "longtext")
  private String content;

  private int rowNo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "file_id", referencedColumnName = "file_id", nullable = false, insertable = false, updatable = false)
  private Files file;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "sheet_id", referencedColumnName = "sheet_id", nullable = false, insertable = false, updatable = false)
  private Sheets sheet;

}
