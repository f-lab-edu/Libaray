package flab.library.rental.service.query;

import flab.library.rental.domain.RentalUserList;

public interface RentalQueryService {
    RentalUserList getRentalUserList(String isbn);
}
