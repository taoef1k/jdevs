package id.vimona.demo.jetdevs_test.mapper;

import id.vimona.demo.jetdevs_test.domain.Roles;
import id.vimona.demo.jetdevs_test.model.RolesDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RolesServiceMapper {
  RolesDTO mapToDTO(Roles roles);
}
