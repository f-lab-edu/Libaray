package flab.library.user.service.implement;

import org.springframework.stereotype.Service;

import flab.library.user.domain.entity.LibUser;
import flab.library.user.dto.SignUpDto;
import flab.library.user.repository.LibUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibUserModificationServiceImpl {

	private final LibUserRepository libUserRepository;
	private final LibUserMapper libUserMapper;

	void insertLibUser(SignUpDto.Request signUpDto){
		LibUser libUser = libUserMapper.toEntityFromSignUpDTO(signUpDto);
		libUserRepository.save(libUser);
	}
}
