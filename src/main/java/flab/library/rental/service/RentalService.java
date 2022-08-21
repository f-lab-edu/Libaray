package flab.library.rental.service;

import flab.library.book.domain.entity.Book;
import flab.library.rental.domain.entity.Rental;
import flab.library.user.domain.entity.LibUser;
import java.time.LocalDateTime;

public interface RentalService {
  Rental rentalBook(LibUser user, Book book, LocalDateTime endDate);
}
