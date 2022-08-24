package flab.library.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class SignInDto {

    @Getter
    @AllArgsConstructor @Builder
    static public class Response{
        String id;
    }
}
