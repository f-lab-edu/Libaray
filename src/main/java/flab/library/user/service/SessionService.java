package flab.library.user.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import flab.library.user.domain.UserVO;

public interface SessionService {
	UserVO getCurrentUserVO();
	UserDetails getCurrentUserDetails();
	UserVO setCurrentUserVO();
}
