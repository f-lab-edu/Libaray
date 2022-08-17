package flab.library.user.service;

import java.util.List;

import flab.library.user.domain.UserVO;
import flab.library.user.dto.SignUpDto;

public interface LibUserService {

	UserVO getLibUser(String id);
	List<UserVO> getLibUserList();
	void signUp(SignUpDto.Request signUpDto);

}
