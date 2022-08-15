package flab.library.book.service.command;

import flab.library.book.domain.entity.Book;

public interface BookService {
  Long createBook(Book book);

  void deleteById(Long bookId);
}
