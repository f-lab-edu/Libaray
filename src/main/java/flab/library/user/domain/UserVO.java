package flab.library.user.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserVO {
    String id;
    List<String> books;
}
