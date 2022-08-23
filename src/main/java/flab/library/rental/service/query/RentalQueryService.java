package flab.library.rental.service.query;

import flab.library.rental.domain.RentalUsers;
import flab.library.rental.domain.entity.Rental;

public interface RentalQueryService {
    RentalUsers getRentalUsers(String isbn);
    Long getLateFee(Rental rental);
}
