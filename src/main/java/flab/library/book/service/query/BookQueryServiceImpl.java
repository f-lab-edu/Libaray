package flab.library.book.service.query;

import flab.library.book.domain.entity.Book;
import flab.library.book.dto.BookDto;
import flab.library.book.infrastructure.cache.BookCacheStore;
import flab.library.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookQueryServiceImpl implements BookQueryService{

  private final BookRepository bookRepository;
  private final BookCacheStore bookCacheStore;

  @Override
  public Page<Book> getBooks(Pageable pageable) {
    return bookRepository.findAll(pageable);
  }

  @Override
  public List<BookDto.BookRentalRank> getBookRank(Pageable pageable) {
    List<BookDto.BookRentalRank> bookRanks = new ArrayList<>();
    Set<ZSetOperations.TypedTuple<String>> bookRank = bookCacheStore.getRentalRanksWithScore(pageable);
    List<String> isbnList
            = bookRank.stream().map(ZSetOperations.TypedTuple::getValue).collect(Collectors.toList());
    HashMap<String, Book> bookMap = bookRepository.findAllByISBN(isbnList);

    long rank = pageable.getOffset() * pageable.getPageSize() + 1;
    for (ZSetOperations.TypedTuple<String> ranking : bookRank) {
      String isbn = ranking.getValue();
      Long rentalCount = Long.parseLong(String.valueOf(ranking.getScore())) ;
      Book book = bookMap.get(isbn);
      bookRanks.add(new BookDto.BookRentalRank(rank, rentalCount, book));
      rank++;
    }
    return bookRanks;
  }
}
