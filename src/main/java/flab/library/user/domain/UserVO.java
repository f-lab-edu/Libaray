package flab.library.user.domain;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserVO {
    String id;
    List<UserBookVO> books;

    @Builder
    @Getter
    public static class UserBookVO{
        String title;
        String isbn;
        LocalDateTime startDate;
        LocalDateTime endDate;
    }
}
