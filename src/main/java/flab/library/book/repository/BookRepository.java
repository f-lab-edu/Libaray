package flab.library.book.repository;

import flab.library.book.domain.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashMap;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b.isbn, b " +
            "from Book b " +
            "where b.isbn in (:isbnList) " +
            "group by b.isbn ")
    HashMap<String, Book> findAllByISBN(@Param("isbnList") List<String> isbnList);
}
