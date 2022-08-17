package flab.library.book.service.query;

import flab.library.book.domain.entity.Book;
import flab.library.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookQueryServiceImpl implements BookQueryService{

  private final BookRepository bookRepository;

  @Override
  public Page<Book> getBooks(Pageable pageable) {
    return bookRepository.findAll(pageable);
  }
}
