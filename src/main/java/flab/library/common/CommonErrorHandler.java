package flab.library.common;

import flab.library.common.dto.CommonResponse;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class CommonErrorHandler {
  // 컨트롤 하지 못한  Exception, Error 발생시 호출
  @ResponseBody
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = Exception.class)
  public CommonResponse onException(Exception e) {
    log.error("[ {} ] : {}", e.getClass(), e.getMessage());
    //Todo: 공통 에러코드 정의할 필요가 있다.
    return CommonResponse.fail("알 수 없는 에러가 발생 하였습니다.", 500);
  }

  // @Valid 로 검증된 에러 공통 Advice
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = ConstraintViolationException.class)
  public CommonResponse onConstraintViolationException(ConstraintViolationException e) {
    log.error("[ {} ] : {}", e.getClass(), e.getMessage());
    //Todo: 공통 에러코드 정의할 필요가 있다.
    return CommonResponse.fail(e.getMessage(), 500);
  }
}
