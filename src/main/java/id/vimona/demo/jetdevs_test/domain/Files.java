package id.vimona.demo.jetdevs_test.domain;

import id.vimona.demo.jetdevs_test.model.FileProgress;
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
  sql = "update files set deleted_at = ROUND(UNIX_TIMESTAMP(CURTIME(4)) * 1000) where file_id = ?")
@Table(name = "files")
@Where(clause = "deleted_at = 0")
public class Files extends Auditable<String> {

  @Id
  @Column(name = "file_id", nullable = false, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long fileId;

  @Column(length = 150)
  private String name;

  @Column(columnDefinition = "varchar(15) DEFAULT 'UNREVIEWED'")
  @Enumerated(EnumType.STRING)
  private FileProgress status;

  private Integer checkingPercentage;

  @OneToMany(mappedBy = "file")
  private Set<Contents> contents;

  @OneToMany(mappedBy = "file")
  private Set<Sheets> sheets;

}
