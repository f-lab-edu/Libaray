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

  //Todo: Controller에서 bookId에 대한 검증이 필요함. (require)
  public void deleteBook(Long bookId) {
    bookService.deleteById(bookId);
  }
}
