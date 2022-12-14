package flab.library.book.service.facade;

import static flab.library.book.dto.BookRequestDto.*;
import static flab.library.book.dto.BookResponseDto.*;

import flab.library.book.domain.entity.Book;
import flab.library.book.dto.BookDto;
import flab.library.book.dto.BookDto.BookUpdateDto;
import flab.library.book.service.command.BookService;
import flab.library.book.service.query.BookQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookFacade {
  private final BookService bookService;
  private final BookQueryService bookQueryService;

  public BookCreateResponse createBook(BookCreateRequest request) {
    Book book = request.toEntity();
    Long saveBookId = bookService.createBook(book);
    return new BookCreateResponse(saveBookId);
  }

  public void deleteBook(Long bookId) {
    bookService.deleteById(bookId);
  }

  public BookUpdateResponse updateBook(Long bookId, BookUpdateRequest request) {
    BookUpdateDto updateDto = request.toDto();
    Book book = bookService.updateBook(bookId, updateDto);
    return new BookUpdateResponse(book);
  }

  public Page<BookResponse> getAllBooks(Pageable pageable) {
    return bookQueryService.getBooks(pageable).map(BookResponse::new);
  }

  public List<BookDto.BookRentalRank> getRankBooks(Pageable pageable) {
    return bookQueryService.getBookRank(pageable);
  }
}
