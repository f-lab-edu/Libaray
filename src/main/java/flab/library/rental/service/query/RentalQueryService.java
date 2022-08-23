package flab.library.rental.service.query;

import flab.library.rental.domain.RentalUsers;
import flab.library.rental.domain.entity.Rental;

public interface RentalQueryService {
    RentalUsers getRentalUserList(String isbn);
    Long getLateFee(Rental rental);
}
