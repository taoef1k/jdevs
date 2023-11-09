package id.vimona.demo.jetdevs_test.service;

import id.vimona.demo.jetdevs_test.domain.Permissions;
import id.vimona.demo.jetdevs_test.model.PermissionsDTO;
import id.vimona.demo.jetdevs_test.repos.PermissionsRepository;
import id.vimona.demo.jetdevs_test.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PermissionsService {

  private final PermissionsRepository permissionsRepository;

  public PermissionsService(final PermissionsRepository permissionsRepository) {
    this.permissionsRepository = permissionsRepository;
  }

  public List<PermissionsDTO> findAll() {
    final List<Permissions> permissionses = permissionsRepository.findAll(Sort.by("permissionId"));
    return permissionses.stream()
      .map(permissions -> mapToDTO(permissions, new PermissionsDTO()))
      .toList();
  }

  public PermissionsDTO get(final Long permissionId) {
    return permissionsRepository.findById(permissionId)
      .map(permissions -> mapToDTO(permissions, new PermissionsDTO()))
      .orElseThrow(NotFoundException::new);
  }

  public Long create(final PermissionsDTO permissionsDTO) {
    final Permissions permissions = new Permissions();
    mapToEntity(permissionsDTO, permissions);
    return permissionsRepository.save(permissions).getPermissionId();
  }

  public void update(final Long permissionId, final PermissionsDTO permissionsDTO) {
    final Permissions permissions = permissionsRepository.findById(permissionId)
      .orElseThrow(NotFoundException::new);
    mapToEntity(permissionsDTO, permissions);
    permissionsRepository.save(permissions);
  }

  public void delete(final Long permissionId) {
    permissionsRepository.deleteById(permissionId);
  }

  private PermissionsDTO mapToDTO(final Permissions permissions,
                                  final PermissionsDTO permissionsDTO) {
    permissionsDTO.setPermissionId(permissions.getPermissionId());
    permissionsDTO.setName(permissions.getName());
    return permissionsDTO;
  }

  private Permissions mapToEntity(final PermissionsDTO permissionsDTO,
                                  final Permissions permissions) {
    permissions.setName(permissionsDTO.getName());
    return permissions;
  }

  public boolean nameExists(final String name) {
    return permissionsRepository.existsByNameIgnoreCase(name);
  }

}
