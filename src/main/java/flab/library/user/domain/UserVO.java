package flab.library.user.domain;

import java.time.LocalDateTime;
import java.util.List;

import flab.library.common.exception.BusinessException;
import flab.library.common.exception.BusinessExceptionDictionary;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserVO {
    String id;
    List<UserBookVO> books;

    public Long getTotalLateFee(){
        return books.stream().map(userBookVO -> userBookVO.lateFee)
                .reduce(Long::sum)
                .orElseThrow(() -> BusinessException.create(BusinessExceptionDictionary.CALC_LATE_FEE));
    }

    @Builder
    @Getter
    public static class UserBookVO{
        String title;
        String isbn;
        LocalDateTime startDate;
        LocalDateTime endDate;
        Long lateFee;
    }
}
