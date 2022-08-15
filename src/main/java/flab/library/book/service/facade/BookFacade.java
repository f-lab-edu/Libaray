package flab.library.book.service.facade;

import static flab.library.book.dto.BookRequestDto.*;

import flab.library.book.domain.entity.Book;
import flab.library.book.service.command.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookFacade {
  private final BookService bookService;

  public Long createBook(BookCreateRequest request) {
    Book book = request.toEntity();
    return bookService.createBook(book);
  }

}
