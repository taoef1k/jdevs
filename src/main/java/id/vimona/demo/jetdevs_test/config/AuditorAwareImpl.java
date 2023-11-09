package id.vimona.demo.jetdevs_test.config;

import id.vimona.demo.jetdevs_test.util.AppUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
  @Override
  public Optional<String> getCurrentAuditor() {
    if (AppUtil.isAuthenticated()) {
      return Optional.of(AppUtil.getAuthUsername());
    }
    return Optional.of("anonymous");
  }
}
