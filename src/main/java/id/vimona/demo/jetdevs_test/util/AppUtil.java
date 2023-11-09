package id.vimona.demo.jetdevs_test.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

@Slf4j
public class AppUtil {

  private AppUtil() {
    throw new IllegalArgumentException();
  }

  public static boolean isAuthenticated() {
    return Objects.nonNull(SecurityContextHolder.getContext().getAuthentication());
  }

  public static String getAuthUsername() {
    if (!AppUtil.isAuthenticated()) {
      return Strings.EMPTY;
    }
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }
}
