package flab.library.book.service.command;

import flab.library.book.domain.entity.Book;
import flab.library.book.dto.BookDto.BookUpdateDto;
import flab.library.book.repository.BookRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    //Todo: RuntimeException CustomException으로 변경 필요
    bookRepository.findById(bookId).ifPresentOrElse(Book::delete, RuntimeException::new);
  }

  @Override
  public Book updateBook(Long bookId, BookUpdateDto updateDto) {
    //Todo: RuntimeException CustomException으로 변경 필요
    Book findBook = bookRepository.findById(bookId).orElseThrow(RuntimeException::new);
    findBook.update(updateDto);
    return findBook;
  }
}
