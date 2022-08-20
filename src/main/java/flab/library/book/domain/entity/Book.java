package flab.library.book.domain.entity;

import static flab.library.book.dto.BookDto.*;
import static flab.library.common.exception.BusinessExceptionDictionary.*;


import flab.library.book.dto.BookDto;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import flab.library.common.exception.BusinessException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  private String isbn;
  @Enumerated(EnumType.STRING)
  private BookCategory category;

  private LocalDateTime deletedBy;

  @Builder
  public Book(String title, String content, String isbn, BookCategory category) {
    this.title = title;
    this.content = content;
    this.isbn = isbn;
    this.category = category;
  }

  public void delete() {
    if(this.deletedBy != null) throw BusinessException.create(DELETED_ENTITY_EXCEPTION);
    this.deletedBy = LocalDateTime.now();
  }
  public void update(BookUpdateDto updateDto) {
    this.title = updateDto.getTitle();
    this.content = updateDto.getContent();
  }
}
