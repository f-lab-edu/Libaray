package flab.library.common;

import java.util.ArrayList;
import java.util.List;

import flab.library.common.dto.CommonResponse;

import javax.validation.ConstraintViolationException;

import flab.library.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class CommonErrorHandler {
	// AGJ :  Spring 기본 제공 Exception 까지 전부 Handling
	// 			-> handling scope 가 너무 광범위해져 comment out
	// 			(ex. HttpRequestMethodNotSupportedException)
	// 컨트롤 하지 못한  Exception, Error 발생시 호출
	// @ResponseBody
	// @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	// @ExceptionHandler(value = Exception.class)
	// public CommonResponse onException(Exception e) {
	// 	log.error("[ {} ] : {}", e.getClass(), e.getMessage());
	// 	//Todo: 공통 에러코드 정의할 필요가 있다.
	// 	return CommonResponse.fail("알 수 없는 에러가 발생 하였습니다.", 500);
	// }

	// @Valid 로 검증된 에러 공통 Advice
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = ConstraintViolationException.class)
	public CommonResponse onConstraintViolationException(ConstraintViolationException e) {
		log.error("[ {} ] : {}", e.getClass(), e.getMessage());
		//Todo: 공통 에러코드 정의할 필요가 있다.
		return CommonResponse.fail(e.getMessage(), 500);
	}

	// @Valid 로 검증된 에러 공통 Advice
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public CommonResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		log.error("[ {} ] : {}", e.getClass(), e.getMessage());
		//Todo: 공통 에러코드 정의할 필요가 있다.
		List<String> fieldErrors = new ArrayList<>();
		for(FieldError error : e.getBindingResult().getFieldErrors()){
			fieldErrors.add(error.getDefaultMessage());
		}
		return CommonResponse.fail("Valid Error" ,fieldErrors, 501);
	}

	// Business Exception Control
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = BusinessException.class)
	public CommonResponse onBusinessException(BusinessException e) {
		log.error("[ {} ] : {}", e.getClass(), e.getMessage());
		//Todo: 공통 에러코드 정의할 필요가 있다.
		return CommonResponse.fail(e.getMessage(), 400);
	}

}
