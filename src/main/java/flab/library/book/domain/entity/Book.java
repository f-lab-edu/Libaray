package flab.library.book.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    //Todo: Business(Custom) Exception 으로 교체하기. + message 추가 필요
    if(this.deletedBy != null) throw new RuntimeException();
    this.deletedBy = LocalDateTime.now();
  }
}
