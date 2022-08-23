package flab.library.book.service.query;

import flab.library.book.domain.entity.Book;
import flab.library.book.dto.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static flab.library.book.dto.BookDto.*;

public interface BookQueryService {
  Page<Book> getBooks(Pageable pageable);
  List<BookRentalRank> getBookRank(Pageable pageable);
}
