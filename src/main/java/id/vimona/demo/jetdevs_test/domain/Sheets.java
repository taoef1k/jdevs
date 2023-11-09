package id.vimona.demo.jetdevs_test.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Set;


@Entity
@Getter
@Setter
@SQLDelete(
  sql = "update sheets set deleted_at = ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000) where sheet_id = ?")
@Table(name = "sheets")
@Where(clause = "deleted_at = 0")
public class Sheets extends Auditable<String> {

  @Id
  @Column(name = "sheet_id",nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long sheetId;

  @Column(name = "file_id", nullable = false)
  private Long fileId;

  @Column(nullable = false, length = 150)
  private String name;

  private String header;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "file_id", referencedColumnName = "file_id", nullable = false, insertable = false, updatable = false)
  private Files file;

  @OneToMany(mappedBy = "sheet")
  private Set<Contents> contents;

}
