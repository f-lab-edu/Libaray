package flab.library.user.service.implement;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import flab.library.user.domain.UserVO;
import flab.library.user.service.LibUserService;
import flab.library.user.service.SessionService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

	private final LibUserService libUserService;
	private final HttpSession httpSession;

	@Override
	public UserVO getCurrentUserVO() {
		User user = (User) getCurrentUserDetails();
		UserVO userVO = (UserVO) httpSession.getAttribute(user.getUsername());
		if(userVO == null){
			userVO = setCurrentUserVO();
		}

		return userVO;
	}

	@Override
	public UserVO setCurrentUserVO() {
		User user = (User) getCurrentUserDetails();
		UserVO userVO = libUserService.getLibUser(user.getUsername());
		httpSession.setAttribute(userVO.getId(), userVO);
		return userVO;
	}

	@Override
	public UserDetails getCurrentUserDetails(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			return (UserDetails) principal;
		} else {
			String username = principal.toString();
			return User.builder()
				.username(username)
				.password("anonymousUser")
				.authorities(authentication.getAuthorities())
				.build();
		}
	}
}
