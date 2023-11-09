package id.vimona.demo.jetdevs_test.mapper;

import id.vimona.demo.jetdevs_test.domain.Users;
import id.vimona.demo.jetdevs_test.model.UsersDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD,
  nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsersServiceMapper {
  UsersDTO mapToDTO(Users users);
}
