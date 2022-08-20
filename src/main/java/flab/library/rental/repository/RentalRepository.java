package flab.library.rental.repository;

import flab.library.rental.domain.entity.Rental;
import flab.library.rental.domain.entity.RentalId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, RentalId> {
}
