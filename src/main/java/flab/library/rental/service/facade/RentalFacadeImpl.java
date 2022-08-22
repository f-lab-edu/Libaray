package flab.library.rental.service.facade;

import flab.library.book.domain.entity.Book;
import flab.library.rental.domain.RentalUserList;
import flab.library.rental.domain.entity.Rental;
import flab.library.rental.dto.BookRental;
import flab.library.rental.service.command.RentalService;
import flab.library.rental.service.query.RentalQueryService;
import flab.library.user.domain.entity.LibUser;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalFacadeImpl implements RentalFacade{
  private final RentalService rentalService;
  private final RentalQueryService rentalQueryService;

  @Override
  public BookRental rentalBook(LibUser user, Long bookId, LocalDateTime endDate) {
    Rental rentalBook = rentalService.createRentalBook(user, bookId, endDate);
    return null; // Rental - to DTO -> BookRental
  }

  @Override
  public RentalUserList getRentalUserList(String isbn) {
    return rentalQueryService.getRentalUserList(isbn);
  }

  @Override
  public void renew(Long rentalId) {
    rentalService.renew(rentalId);
  }
}
