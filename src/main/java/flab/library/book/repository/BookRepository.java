package flab.library.book.repository;

import flab.library.book.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashMap;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    HashMap<String, Book> findAllByISBN(List<String> isbnList);
}
