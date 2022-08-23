package flab.library.rental.service.query;

import flab.library.rental.domain.RentalUsers;
import flab.library.rental.domain.entity.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RentalQueryService {
    RentalUsers getRentalUsers(String isbn);
    Long getLateFee(Rental rental);

    Page<Rental> getOngoingRentals(Pageable pageable);
}
