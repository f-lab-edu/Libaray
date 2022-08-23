package flab.library.rental.service.facade;

import flab.library.rental.domain.RentalUsers;
import flab.library.rental.dto.BookRentalResponseDto;
import flab.library.user.domain.entity.LibUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

import static flab.library.rental.dto.BookRentalResponseDto.*;


public interface RentalFacade {

  BookRentalResponseDto rentalBook(LibUser user, Long bookId, LocalDateTime endDate);
  RentalUsers getRentalUserList(String isbn);
  void renew(Long rentalId);
  void returnBooks(Long rentalId);

  Page<BookRentalInfo> getOngoingRentalInfo(Pageable pageable);
}
