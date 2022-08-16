package flab.library.book.service.command;

import flab.library.book.domain.entity.Book;
import flab.library.book.dto.BookDto;

public interface BookService {
  Long createBook(Book book);

  void deleteById(Long bookId);
  Book updateBook(Long bookId, BookDto.BookUpdateDto updateDto);
}
