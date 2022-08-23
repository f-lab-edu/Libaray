package flab.library.rental.domain.entity;

import flab.library.book.domain.entity.Book;
import flab.library.user.domain.entity.LibUser;

import java.time.Instant;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Rental {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 독립적인 식별자는 필요 없을지 추후 논의하고 싶은 부분이 있습니다.
     * 추가적으로 전부 참조 관계로 유지해도 될지도 논의해보고 싶습니다.
     */
//    @EmbeddedId
//    RentalId rentalId;

    private LocalDateTime returnDate;
    private boolean renew;

    @ManyToOne(targetEntity = LibUser.class)
    @JoinColumn(name="user_id")
    private LibUser user;

    @ManyToOne(targetEntity = Book.class)
    @JoinColumn(name="book_id")
    Book book;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Builder
    private Rental(LibUser user, Book book, LocalDateTime endDate) {
        this.returnDate = null;
        this.user = user;
        this.book = book;
        this.endDate = endDate;
    }
    public static Rental createRental(LibUser user, LocalDateTime endDate, Book findBook) {
        return Rental.builder()
            .book(findBook)
            .endDate(endDate)
            .user(user)
            .build();
    }

}
