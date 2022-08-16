package flab.library.book.dto;

import flab.library.book.domain.entity.Book;
import flab.library.book.domain.entity.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class BookResponseDto {

  @Getter
  public static class BookCreateResponse {
    private Long saveBookId;

    public BookCreateResponse(Long saveBookId) {
      this.saveBookId = saveBookId;
    }
  }

  @Getter
  public static class BookUpdateResponse {
    private String title;
    private String content;
    private String isbn;
    private BookCategory category;

    public BookUpdateResponse(Book book) {
      this.title = book.getTitle();
      this.content = book.getContent();
      this.isbn = book.getIsbn();
      this.category = book.getCategory();
    }
  }
}
