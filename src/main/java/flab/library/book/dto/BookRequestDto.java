package flab.library.book.dto;


import flab.library.book.domain.entity.Book;
import flab.library.book.domain.entity.BookCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BookRequestDto {
  @Data
  public static class BookCreateRequest {
    private String title;
    private String content;
    private String isbn;
    private BookCategory category;

    public Book toEntity() {
      return Book.builder()
          .title(this.title)
          .content(this.content)
          .isbn(this.isbn)
          .category(this.category)
          .build();
    }
  }

  @Data
  @AllArgsConstructor @NoArgsConstructor
  public static class BookUpdateRequest {
    private String title;
    private String content;

    public BookDto.BookUpdateDto toDto() {
      return new BookDto.BookUpdateDto(title, content);
    }
  }

}
