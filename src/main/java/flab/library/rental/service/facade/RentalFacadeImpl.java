package flab.library.rental.service.facade;

import flab.library.book.domain.entity.Book;
import flab.library.rental.domain.entity.Rental;
import flab.library.rental.dto.BookRental;
import flab.library.rental.service.command.RentalService;
import flab.library.user.domain.entity.LibUser;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalFacadeImpl implements RentalFacade{
  private final RentalService rentalService;

  @Override
  public BookRental rentalBook(LibUser user, Long bookId, LocalDateTime endDate) {
    Book findBook = null; // bookId로 찾아오기. BookService 등에서.
    Rental rentalBook = rentalService.createRentalBook(user, findBook, endDate);
    return null; // Rental - to DTO -> BookRental
  }
}
