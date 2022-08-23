package flab.library.rental.dto;

import flab.library.book.domain.entity.Book;
import flab.library.common.exception.BusinessException;
import flab.library.common.exception.BusinessExceptionDictionary;
import flab.library.rental.domain.entity.Rental;

import java.time.LocalDateTime;

public class BookRentalResponseDto {

    public static class BookRentalInfo {
        private String title;
        private String isbn;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private boolean isRenew;

        public BookRentalInfo(Rental rental) {
            if (rental == null) BusinessException.create(BusinessExceptionDictionary.RENTAL_NOT_FOUND_EXCEPTION);
            Book book = rental.getBook();
            if (book == null) BusinessException.create(BusinessExceptionDictionary.BOOK_NOT_FOUND_EXCEPTION);
            this.title = book.getTitle();
            this.isbn = book.getIsbn();
            this.startDate = rental.getStartDate();
            this.endDate = rental.getEndDate();
            this.isRenew = rental.isRenew();
        }
    }


}
