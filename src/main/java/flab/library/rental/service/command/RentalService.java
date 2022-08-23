package flab.library.rental.service.command;

import flab.library.book.domain.entity.Book;
import flab.library.rental.domain.entity.Rental;
import flab.library.user.domain.entity.LibUser;
import java.time.LocalDateTime;

public interface RentalService {
  Rental createRentalBook(LibUser user, Long bookId, LocalDateTime endDate);
  void renew(Long rentalId);
  void returnBook(Long rentalId);
}
