package flab.library.rental.service.query;

import flab.library.rental.domain.RentalUserList;
import flab.library.rental.domain.entity.Rental;
import flab.library.rental.repository.RentalRepository;
import flab.library.rental.service.RentalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalQueryServiceImpl implements RentalQueryService{

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    @Override
    public RentalUserList getRentalUserList(String isbn) {
        List<Rental> rentalList = rentalRepository.findAllRentalByBookISBN(isbn);

        return RentalUserList.builder()
                .rentalUserList(
                        rentalList.stream()
                                .map(rentalMapper::toRentalUser)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
