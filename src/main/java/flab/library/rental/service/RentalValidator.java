package flab.library.rental.service;

import static flab.library.common.exception.BusinessExceptionDictionary.*;

import flab.library.book.domain.entity.Book;
import flab.library.common.exception.BusinessException;
import flab.library.common.exception.BusinessExceptionDictionary;
import flab.library.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalValidator {
  private final RentalRepository rentalRepository;

  public void checkRentedBook(Book book) {
    if (rentalRepository.existRentedBookBy(book)) {
      throw BusinessException.create(ALREADY_RENTAL_BOOK_EXCEPTION);
    };
  }
}
