package flab.library.user.controller;

import java.util.List;

import javax.validation.Valid;

import flab.library.common.dto.CommonResponse;
import flab.library.common.exception.BusinessException;
import flab.library.common.exception.BusinessExceptionDictionary;
import flab.library.user.domain.UserVO;
import flab.library.user.domain.entity.LibUser;
import flab.library.user.dto.SignInDto;
import flab.library.user.dto.SignUpDto;
import flab.library.user.dto.UpdatePwdDto;
import flab.library.user.service.LibUserService;
import flab.library.user.service.SessionService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

	private final SessionService sessionService;
	private final LibUserService libUserService;

	@GetMapping("/hello")
	String hello() {
		return "hello";
	}

	@GetMapping("/unknown")
	String unknown() {
		throw BusinessException.create(BusinessExceptionDictionary.UNKNOWN);
	}

	@GetMapping
	public ResponseEntity<CommonResponse<List<UserVO>>> getUserList() {
		return ResponseEntity.ok().body(CommonResponse.success(libUserService.getLibUserList()));
	}

	@PostMapping("/sign-in")
	public ResponseEntity<CommonResponse<SignInDto.Response>> signIn() {
		User user = (User)sessionService.getCurrentUserDetails();

		return ResponseEntity.ok()
			.body(CommonResponse.success(SignInDto.Response.builder()
				.id(user.getUsername())
				.build()));
	}

	@PostMapping("/sign-up")
	public ResponseEntity<CommonResponse<String>> signUp(
		@RequestBody @Valid SignUpDto.Request signUpDto
	) {
		libUserService.signUp(signUpDto);

		return ResponseEntity.ok()
			.body(CommonResponse.success(null, "SignUp Completed"));
	}

	@PatchMapping("/password")
	public ResponseEntity<CommonResponse<String>> updatePassword(
		@RequestBody @Valid UpdatePwdDto.Request updatePwdDto
	){
		log.info(updatePwdDto.toString());
		User user = (User)sessionService.getCurrentUserDetails();
		updatePwdDto.setId(user.getUsername());

		libUserService.updatePassword(updatePwdDto);

		SecurityContextHolder.clearContext();
		return ResponseEntity.ok()
			.body(CommonResponse.success(null, "Update Password Completed"));
	}

}
