package flab.library.book.dto;


import flab.library.book.domain.entity.Book;
import flab.library.book.domain.entity.BookCategory;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;

public class BookRequestDto {
  @Data
  public static class BookCreateRequest {
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @ISBN
    @NotEmpty
    private String isbn;
    @NotNull
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
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;

    public BookDto.BookUpdateDto toDto() {
      return new BookDto.BookUpdateDto(title, content);
    }
  }

}
