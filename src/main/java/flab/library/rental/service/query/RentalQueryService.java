package flab.library.rental.service.query;

import flab.library.rental.domain.RentalUserList;
import flab.library.rental.domain.entity.Rental;

public interface RentalQueryService {
    RentalUserList getRentalUserList(String isbn);
    Long getLateFee(Rental rental);
}
