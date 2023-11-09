package id.vimona.demo.jetdevs_test.service;

import id.vimona.demo.jetdevs_test.model.RolesDTO;
import id.vimona.demo.jetdevs_test.model.UsersDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private static final String password = "Password"; // For demo purpose
  private static final String PREFIX_ROLE = "ROLE_";
  private static final String PERMISSION_SUFFIX = "_PRIVILEGE";

  private final UsersService usersService;
  private final BCryptPasswordEncoder passwordEncoder;
  private final RolesService rolesService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UsersDTO customer = usersService.getByUsername(username);
    List<RolesDTO> roles = rolesService.findAllByUserId(customer.getUserId());
    Collection<? extends GrantedAuthority> authorities = getAuthorities(roles);
    UserDetails user = User.withUsername(customer.getUsername())
      .password(passwordEncoder.encode(password))
      .authorities(authorities).build();
    return user;
  }

  private Collection<? extends GrantedAuthority> getAuthorities(
    Collection<RolesDTO> roles) {
    List<GrantedAuthority> authorities
      = new ArrayList<>();
    for (RolesDTO role : roles) {
      authorities.add(new SimpleGrantedAuthority(PREFIX_ROLE + role.getCode()));
      role.getRolePermissions().stream()
        .map(rolePermissions -> rolePermissions.getPermission())
        .map(permissions -> new SimpleGrantedAuthority(permissions.getName() + PERMISSION_SUFFIX))
        .forEach(authorities::add);
    }

    return authorities;
  }
}
