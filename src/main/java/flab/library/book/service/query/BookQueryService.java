package flab.library.book.service.query;

import flab.library.book.domain.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookQueryService {
  Page<Book> getBooks(Pageable pageable);
}
