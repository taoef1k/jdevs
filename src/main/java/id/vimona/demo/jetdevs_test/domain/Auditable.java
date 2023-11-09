package id.vimona.demo.jetdevs_test.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U> {
  @JsonIgnore
  @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @CreatedDate
//  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "dd MM yyyy HH:mm")
  protected LocalDateTime createdDate;

  @Column(nullable = false, length = 150)
  @JsonIgnore
  @CreatedBy
  protected U createdBy;

  @JsonIgnore
  @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  @LastModifiedDate
//  @Temporal(TemporalType.TIMESTAMP)
  @DateTimeFormat(pattern = "dd MM yyyy HH:mm")
  protected LocalDateTime lastModifiedDate;

  @JsonIgnore
  @LastModifiedBy
  protected U lastModifiedBy;

  @Column(name = "deleted_at", nullable = false)
  protected final long deletedAt = 0;
}
