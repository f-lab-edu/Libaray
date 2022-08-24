package flab.library.book.dto;

import flab.library.book.domain.entity.Book;
import flab.library.book.domain.entity.BookCategory;
import lombok.Getter;

public class BookDto {

  @Getter
  public static class BookUpdateDto {
    private final String title;
    private final String content;

    public BookUpdateDto(String title, String content) {
      this.title = title;
      this.content = content;
    }
  }

  @Getter
  public static class BookRentalRank {
    private String isbn;
    private String title;
    private Long rank;
    private Long rentalCount;
    private BookCategory category;

    public BookRentalRank(long rank, Long rentalCount, Book book) {
      this.isbn = book.getIsbn();
      this.title = book.getTitle();
      this.rank = rank;
      this.rentalCount = rentalCount;
      this.category = book.getCategory();
    }
  }


}
