package flab.library.rental.domain.entity;

import flab.library.book.domain.entity.Book;
import flab.library.user.domain.entity.LibUser;

import java.time.Instant;
import java.time.LocalDateTime;
import javax.persistence.*;

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

    private LocalDateTime returnDate;
    private boolean renew;

    @ManyToOne(targetEntity = LibUser.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private LibUser user;

    @ManyToOne(targetEntity = Book.class, fetch = FetchType.LAZY)
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

    public void returnBook() {
        this.returnDate = LocalDateTime.now();
    }
}
