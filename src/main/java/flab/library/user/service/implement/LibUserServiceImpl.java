package flab.library.user.service.implement;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import flab.library.user.domain.UserVO;
import flab.library.user.domain.entity.LibUser;
import flab.library.user.dto.SignUpDto;
import flab.library.user.dto.UpdatePwdDto;
import flab.library.user.repository.LibUserRepository;
import flab.library.user.service.LibUserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibUserServiceImpl implements LibUserService {

	private final LibUserQueryServiceImpl libUserQueryService;
	private final LibUserModificationServiceImpl libUserModificationService;

	@Override
	public UserVO getLibUser(String id) {
		return libUserQueryService.getLibUser(id);
	}

	@Override
	public List<UserVO> getLibUserList() {
		return libUserQueryService.getLibUserList();
	}

	@Override
	public void signUp(SignUpDto.Request signUpDto) {
		libUserModificationService.insertLibUser(signUpDto);

	}

	@Override
	public void updatePassword(UpdatePwdDto.Request updatePwdDto) {
		libUserModificationService.updateLibUserPassword(updatePwdDto);
	}
}
