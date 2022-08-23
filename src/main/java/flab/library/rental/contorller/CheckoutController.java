package flab.library.rental.contorller;

import flab.library.common.dto.CommonResponse;
import flab.library.rental.domain.RentalUserList;
import flab.library.rental.service.facade.RentalFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rental")
@RequiredArgsConstructor
public class CheckoutController {
    private final RentalFacade rentalFacade;

    @GetMapping("/isbn/{isbn}")
    ResponseEntity<CommonResponse<RentalUserList>> getRentalUserList(@PathVariable String isbn){
        return ResponseEntity.ok().body(
                CommonResponse.success(rentalFacade.getRentalUserList(isbn)));
    }

    @PatchMapping("/id/{id}/renew")
    ResponseEntity<CommonResponse<String>> renewRental(@PathVariable Long id){
        rentalFacade.renew(id);
        return ResponseEntity.ok().body(CommonResponse.success(null, "Renew Completed"));
    }


}
