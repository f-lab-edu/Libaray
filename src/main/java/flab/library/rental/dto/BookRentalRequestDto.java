package flab.library.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class BookRentalRequestDto {

    @Data
    @NoArgsConstructor @AllArgsConstructor
    public static class RentalRequest {
        @NotNull(message = "대출 만료일은 필수 데이터 입니다.")
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
        private LocalDateTime endDate;
    }
}
