package flab.library.book.service.command;

import flab.library.book.domain.entity.Book;
import flab.library.book.dto.BookDto.BookUpdateDto;
import flab.library.book.repository.BookRepository;
import javax.transaction.Transactional;

import flab.library.common.exception.BusinessException;
import flab.library.common.exception.BusinessExceptionDictionary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static flab.library.common.exception.BusinessExceptionDictionary.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BookServiceImpl implements BookService{
  private final BookRepository bookRepository;

  @Override
  public Long createBook(Book book) {
    Book saveBook = bookRepository.save(book);
    return saveBook.getId();
  }

  @Override
  public void deleteById(Long bookId) {
    bookRepository.findById(bookId).ifPresentOrElse(Book::delete
            , () -> BusinessException.createAndThrow(BOOK_NOT_FOUND_EXCEPTION));
  }

  @Override
  public Book updateBook(Long bookId, BookUpdateDto updateDto) {
    Book findBook = bookRepository.findById(bookId)
            .orElseThrow(() -> BusinessException.create(BOOK_NOT_FOUND_EXCEPTION));
    findBook.update(updateDto);
    return findBook;
  }
}
