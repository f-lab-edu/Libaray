package flab.library.rental.service.command;

import flab.library.book.domain.entity.Book;
import flab.library.book.infrastructure.cache.BookCacheStore;
import flab.library.book.repository.BookRepository;
import flab.library.common.exception.BusinessException;
import flab.library.common.exception.BusinessExceptionDictionary;
import flab.library.config.LibraryPolicyValues;
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
  private final LibraryPolicyValues libraryPolicyValues;
  private final BookCacheStore bookCacheStore;


  @Override
  public Rental createRentalBook(LibUser user, Long bookId, LocalDateTime endDate) {
    Book findBook = bookRepository.findById(bookId).orElseThrow(() -> BusinessException.create(
        BusinessExceptionDictionary.BOOK_NOT_FOUND_EXCEPTION));
    rentalValidator.checkRentedBook(findBook);
    Rental saveRental = rentalRepository.save(Rental.createRental(user, endDate, findBook));
    bookCacheStore.increaseRentalCount(findBook.getIsbn());
    return saveRental;
  }

  @Override
  public void renew(Long rentalId) {
    Rental rental = rentalRepository.findNotReturnedAndNotRenewedRentalById(rentalId)
            .orElseThrow(() -> BusinessException.create(
            BusinessExceptionDictionary.INVALID_RENTAL_ID));
    rental.setEndDate(rental.getEndDate().plusDays(libraryPolicyValues.getRenewDays()));
    rental.setRenew(true);
    rentalRepository.save(rental);
  }

  @Override
  public void returnBook(Long rentalId) {
    Rental rental = rentalRepository.findNotReturnedRentalById(rentalId)
            .orElseThrow(() -> BusinessException.create(
                    BusinessExceptionDictionary.INVALID_RENTAL_ID)
            );
    rental.returnBook();
  }
}
