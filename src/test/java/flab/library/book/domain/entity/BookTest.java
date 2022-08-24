package flab.library.book.domain.entity;

import static flab.library.book.domain.entity.BookCategory.NOVEL;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

class BookTest {
  Book bookOne;

  @BeforeEach
  void setUp() {
    bookOne = new Book("toby's spring", "di is fantastic", "978-3-16-148410-0", NOVEL);
  }

  /**
   * case: 책 삭제 정상 케이스
   * case: 이미 삭제된 책인 경우
   * Todo: case: 현재 대출 중인 책인 경우 <-x- 추후 고려하는 것으로 함.
   */

  @Test
  @DisplayName("Book 삭제 도메인 로직 정상 작동 여부.")
  void delete_Book() {
    //when
    assertThat(bookOne.getDeletedBy()).isNull();
    bookOne.delete();
    //then
    assertThat(bookOne.getDeletedBy()).isNotNull();
  }

  @Test
  @DisplayName("이미 삭제된 책 다시 삭제를 시도할 때")
  void delete_exception_case() {
    //given
    ReflectionTestUtils.setField(bookOne, "deletedBy", LocalDateTime.now());
    //when then
    assertThat(bookOne.getDeletedBy()).isNotNull();
    assertThrows(RuntimeException.class, () -> bookOne.delete());
  }

}