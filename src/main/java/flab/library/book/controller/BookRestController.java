package flab.library.book.controller;

import flab.library.book.dto.BookRequestDto.BookCreateRequest;
import flab.library.book.dto.BookRequestDto.BookUpdateRequest;
import flab.library.book.dto.BookResponseDto.BookCreateResponse;
import flab.library.book.dto.BookResponseDto.BookResponse;
import flab.library.book.dto.BookResponseDto.BookUpdateResponse;
import flab.library.book.service.facade.BookFacade;
import flab.library.common.dto.CommonResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookRestController {
  private final BookFacade bookFacade;

  @PostMapping
  public CommonResponse<BookCreateResponse> saveBook(@Valid @RequestBody BookCreateRequest request) {
    BookCreateResponse bookResponse = bookFacade.createBook(request);
    return CommonResponse.success(bookResponse);
  }

  @PutMapping("/{bookId}")
  public CommonResponse<BookUpdateResponse> updateBook(@Valid @RequestBody BookUpdateRequest request,
      @PathVariable Long bookId) {
    BookUpdateResponse response = bookFacade.updateBook(bookId, request);
    return CommonResponse.success(response);
  }

  @DeleteMapping("/{bookId}")
  public CommonResponse<Void> deleteBook(@PathVariable Long bookId) {
    bookFacade.deleteBook(bookId);
    return CommonResponse.success();
  }

  @GetMapping
  public CommonResponse showBooks(@PageableDefault(page = 0, size = 10) Pageable pageable) {
    Page<BookResponse> allBooks = bookFacade.getAllBooks(pageable);
    return CommonResponse.success(allBooks, "책 전체 목록 조회(page, size) 매개변수를 통해 페이징");
  }

}
