package flab.library.user.service.implement;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import flab.library.user.domain.UserVO;
import flab.library.user.domain.entity.LibUser;
import flab.library.user.dto.SignUpDto;

@Service
public class LibUserMapper {

	public UserVO toVO(LibUser libUser){
		return UserVO.builder()
			.id(libUser.getId())
			.books(new ArrayList<>())
			.build();
	}

	public LibUser toEntityFromVO(UserVO userVO){
		return LibUser.builder()
			.id(userVO.getId())
			.build();

	}

	public LibUser toEntityFromSignUpDTO(SignUpDto.Request signUpDto){
		return LibUser.builder()
			.id(signUpDto.getId())
			.password(signUpDto.getPassword())
			.name(signUpDto.getName())
			.active(true)
			.build();
	}
}
