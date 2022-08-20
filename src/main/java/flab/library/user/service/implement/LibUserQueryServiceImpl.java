package flab.library.user.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import flab.library.user.domain.UserVO;
import flab.library.user.repository.LibUserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LibUserQueryServiceImpl {

	private final LibUserRepository libUserRepository;
	private final LibUserMapper libUserMapper;

	public UserVO getLibUser(String id) {
		return libUserMapper.toVO(
			libUserRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id + "not found")));
	}

	public List<UserVO> getLibUserList() {
		return libUserRepository.findAll().stream().map(libUserMapper::toVO).collect(Collectors.toList());
	}
}
