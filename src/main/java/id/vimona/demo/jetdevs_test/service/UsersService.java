package id.vimona.demo.jetdevs_test.service;

import id.vimona.demo.jetdevs_test.mapper.UsersServiceMapper;
import id.vimona.demo.jetdevs_test.model.UsersDTO;
import id.vimona.demo.jetdevs_test.repos.UsersRepository;
import id.vimona.demo.jetdevs_test.util.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UsersService {

  private final UsersRepository usersRepository;
  private final UsersServiceMapper usersServiceMapper;

  public UsersDTO getByUsername(final String username) {
    return usersRepository.findOneByUsernameIgnoreCase(username)
      .map(users -> usersServiceMapper.mapToDTO(users))
      .orElseThrow(NotFoundException::new);
  }
}
