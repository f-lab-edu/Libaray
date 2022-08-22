package flab.library.rental;

import flab.library.config.LibraryPolicyValues;
import flab.library.rental.domain.entity.Rental;
import flab.library.rental.service.query.RentalQueryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RentalTest {

    private final RentalQueryService rentalQueryService;
    private final LibraryPolicyValues libraryPolicyValues;

    @Autowired
    public RentalTest(RentalQueryService rentalQueryService, LibraryPolicyValues libraryPolicyValues) {
        this.rentalQueryService = rentalQueryService;
        this.libraryPolicyValues = libraryPolicyValues;
    }

    @DisplayName("calculate late fee")
    @Test
    void calcLateFee(){
        LocalDateTime endDate = LocalDateTime
                .ofInstant(Instant.now(), ZoneOffset.ofHours(9))
                .minusDays(10);
        Rental rental = Rental.builder().endDate(endDate).build();

        assertEquals(rentalQueryService.getLateFee(rental), 10 * libraryPolicyValues.getLateFeePerDay());
    }
}
