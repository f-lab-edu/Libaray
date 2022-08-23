package flab.library.rental.service;

import flab.library.rental.domain.RentalUsers;
import flab.library.rental.domain.entity.Rental;
import flab.library.user.domain.entity.LibUser;
import org.springframework.stereotype.Service;

@Service
public class RentalMapper {

    public RentalUsers.RentalUser toRentalUser(Rental rental){
        LibUser libUser = rental.getUser();
        return RentalUsers.RentalUser.builder()
                .id(libUser.getId())
                .startTime(rental.getStartDate())
                .endTime(rental.getEndDate())
                .build();
    }

}
