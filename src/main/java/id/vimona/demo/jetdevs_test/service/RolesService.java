package id.vimona.demo.jetdevs_test.service;

import id.vimona.demo.jetdevs_test.domain.UserRoles;
import id.vimona.demo.jetdevs_test.mapper.RolesServiceMapper;
import id.vimona.demo.jetdevs_test.model.RolesDTO;
import id.vimona.demo.jetdevs_test.repos.RolesRepository;
import id.vimona.demo.jetdevs_test.repos.UserRolesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class RolesService {

  private final RolesRepository rolesRepository;
  private final UserRolesRepository userRolesRepository;
  private final RolesServiceMapper rolesServiceMapper;

  public List<RolesDTO> findAllByUserId(final Long userId) {
    List<UserRoles> userRoles = userRolesRepository.findRoleByUserId(userId);
    return userRoles.stream()
      .map(UserRoles::getRole)
      .map(rolesServiceMapper::mapToDTO)
      .collect(Collectors.toList());
  }

}
