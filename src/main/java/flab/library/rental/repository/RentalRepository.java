package flab.library.rental.repository;

import flab.library.book.domain.entity.Book;
import flab.library.rental.domain.entity.Rental;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RentalRepository extends JpaRepository<Rental, Long> {

  @Query("select count(r) > 0 "
      + "from Rental r "
      + "where r.book = :book "
      + "and r.returnDate is null ")
  boolean existRentedBookBy(@Param("book") Book findBook);

  @Query("select rental from Rental rental join fetch Book book " +
          "on rental.book = book where book.isbn = :isbn")
  List<Rental> findAllRentalByBookISBN(String isbn);

  @Query("select r " +
          "from Rental r " +
          "where r.id = :rentalId " +
          "and r.returnDate is null " +
          "and r.renew = false ")
  Optional<Rental> findNotReturnedAndNotRenewedRentalById(@Param("rentalId") Long rentalId);

  @Query("select r " +
          "from Rental r " +
          "where r.id = :rentalId " +
          "and r.returnDate is null ")
  Optional<Rental> findNotReturnedRentalById(@Param("rentalId") Long rentalId);

  @Query("select rental " +
          "from Rental rental " +
          "join fetch Book book on rental.book = book " +
          "where rental.returnDate is null ")
  Page<Rental> findAllRentalOngoing(Pageable pageable);


}
