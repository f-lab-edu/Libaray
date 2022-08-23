package flab.library.rental.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Builder
public class RentalUsers {
    String title;
    String isbn;
    List<RentalUser> rentalUserList;

    @Getter @Builder
    public static class RentalUser{
        String id;
        LocalDateTime startTime;
        LocalDateTime endTime;
    }
}
