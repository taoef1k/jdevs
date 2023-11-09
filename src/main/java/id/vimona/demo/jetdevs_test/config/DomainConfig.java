package id.vimona.demo.jetdevs_test.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.OffsetDateTime;
import java.util.Optional;


@Configuration
@EntityScan("id.vimona.demo.jetdevs_test.domain")
@EnableJpaRepositories("id.vimona.demo.jetdevs_test.repos")
@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "auditingDateTimeProvider")
public class DomainConfig {

  @Bean(name = "auditingDateTimeProvider")
  public DateTimeProvider dateTimeProvider() {
    return () -> Optional.of(OffsetDateTime.now());
  }

  @Bean
  public AuditorAware<String> auditorProvider() {
    return new AuditorAwareImpl();
  }

}
