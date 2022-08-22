package flab.library.rental.repository;

import flab.library.book.domain.entity.Book;
import flab.library.rental.domain.entity.Rental;
import flab.library.rental.domain.entity.RentalId;

import java.util.List;
import java.util.Optional;

import flab.library.user.domain.entity.LibUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentalRepository extends JpaRepository<Rental, Long> {

  @Query("select count(r) > 0 "
      + "from Rental r "
      + "where r.book = :book "
      + "and r.isReturn = false ")
  boolean existRentedBookBy(@Param("book") Book findBook);

  @Query("select rental from Rental rental join fetch Book book " +
          "on rental.book = book where book.isbn = :isbn")
  List<Rental> findAllRentalByBookISBN(String isbn);

  Optional<Rental> findRentalByIdAndIsReturnIsFalseAndRenewIsFalse(Long id);
}
