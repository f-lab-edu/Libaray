package flab.library.rental.service.command;

import flab.library.book.domain.entity.Book;
import flab.library.rental.domain.entity.Rental;
import flab.library.rental.repository.RentalRepository;
import flab.library.rental.service.RentalService;
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


  @Override
  public Rental rentalBook(LibUser user, Book book, LocalDateTime endDate) {
    if (rentalRepository.existRentedBookBy(book)) {
      // 비즈니스 에러로 변경 필요. ("이미 대여된 목록입니다.")
      throw new RuntimeException();
    };

    return rentalRepository.save(
      Rental.createRental(user, endDate, book)
    );
  }

}
