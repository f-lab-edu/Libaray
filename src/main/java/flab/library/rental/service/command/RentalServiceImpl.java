package flab.library.rental.service.command;

import flab.library.book.domain.entity.Book;
import flab.library.book.repository.BookRepository;
import flab.library.common.exception.BusinessException;
import flab.library.common.exception.BusinessExceptionDictionary;
import flab.library.rental.domain.entity.Rental;
import flab.library.rental.repository.RentalRepository;
import flab.library.rental.service.RentalValidator;
import flab.library.user.domain.entity.LibUser;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
  private final RentalRepository rentalRepository;
  private final BookRepository bookRepository;
  private final RentalValidator rentalValidator;


  @Override
  public Rental createRentalBook(LibUser user, Long bookId, LocalDateTime endDate) {
    Book findBook = bookRepository.findById(bookId).orElseThrow(() -> BusinessException.create(
        BusinessExceptionDictionary.BOOK_NOT_FOUND_EXCEPTION));
    rentalValidator.checkRentedBook(findBook);
    return rentalRepository.save(
      Rental.createRental(user, endDate, findBook)
    );
  }

}
