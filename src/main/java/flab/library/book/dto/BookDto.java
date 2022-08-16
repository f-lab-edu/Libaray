package flab.library.book.dto;

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


}
