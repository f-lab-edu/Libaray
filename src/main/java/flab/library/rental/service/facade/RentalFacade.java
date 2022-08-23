package flab.library.rental.service.facade;

import flab.library.rental.domain.RentalUserList;
import flab.library.rental.dto.BookRental;
import flab.library.user.domain.entity.LibUser;
import java.time.LocalDateTime;


public interface RentalFacade {

  BookRental rentalBook(LibUser user, Long bookId, LocalDateTime endDate);
  RentalUserList getRentalUserList(String isbn);
  void renew(Long rentalId);
  void returnBooks(Long rentalId);
}
