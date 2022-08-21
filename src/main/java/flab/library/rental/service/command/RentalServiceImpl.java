package flab.library.rental.service.command;

import flab.library.book.domain.entity.Book;
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
  private final RentalValidator rentalValidator;


  @Override
  public Rental createRentalBook(LibUser user, Book book, LocalDateTime endDate) {
    rentalValidator.checkRentedBook(book);
    return rentalRepository.save(
      Rental.createRental(user, endDate, book)
    );
  }

}
