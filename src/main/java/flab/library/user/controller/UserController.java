package flab.library.user.controller;

import flab.library.common.dto.CommonResponse;
import flab.library.user.domain.UserVO;
import flab.library.user.domain.entity.LibUser;
import flab.library.user.dto.SignInDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @GetMapping
    String hello(){
        return "hello";
    }

    @PostMapping("/sign-in")
    public ResponseEntity<CommonResponse<SignInDto.Response>> signIn() throws Exception {
//        UserVO userVO = sessionService.getCurrentUserVO();

        return ResponseEntity.ok()
                .body(CommonResponse.success(SignInDto.Response.builder()
                                .id("test")
                        .build()));
    }

}
