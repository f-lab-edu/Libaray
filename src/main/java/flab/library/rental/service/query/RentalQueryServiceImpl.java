package flab.library.rental.service.query;

import flab.library.config.LibraryPolicyValues;
import flab.library.rental.domain.RentalUsers;
import flab.library.rental.domain.entity.Rental;
import flab.library.rental.repository.RentalRepository;
import flab.library.rental.service.RentalMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class RentalQueryServiceImpl implements RentalQueryService{

    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final LibraryPolicyValues libraryPolicyValues;

    @Override
    public RentalUsers getRentalUsers(String isbn) {
        List<Rental> rentalList = rentalRepository.findAllRentalByBookISBN(isbn);

        return RentalUsers.builder()
                .rentalUsers(
                        rentalList.stream()
                                .map(rentalMapper::toRentalUser)
                                .collect(Collectors.toList())
                )
                .build();
    }

    @Override
    public Long getLateFee(Rental rental){
        LocalDateTime current = LocalDateTime.ofInstant(Instant.now(), ZoneOffset.ofHours(9));
        long days = DAYS.between(rental.getEndDate(), current);
        return days <= 0L ? 0L : days  * libraryPolicyValues.getLateFeePerDay();
    }

    @Override
    public Page<Rental> getOngoingRentals(Pageable pageable) {
        return rentalRepository.findAllRentalOngoing(pageable);
    }
}
