package flab.library.rental.service;

import flab.library.book.domain.entity.Book;
import flab.library.rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RentalValidator {
  private final RentalRepository rentalRepository;

  public void checkRentedBook(Book book) {
    if (rentalRepository.existRentedBookBy(book)) {
      // 비즈니스 에러로 변경 필요. ("이미 대여된 목록입니다.")
      throw new RuntimeException();
    };
  }
}
