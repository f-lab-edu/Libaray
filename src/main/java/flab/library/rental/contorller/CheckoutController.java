package flab.library.rental.contorller;

import flab.library.common.dto.CommonResponse;
import flab.library.rental.domain.RentalUsers;
import flab.library.rental.dto.BookRentalResponseDto;
import flab.library.rental.service.facade.RentalFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static flab.library.rental.dto.BookRentalResponseDto.*;

@RestController
@RequestMapping("/v1/rental")
@RequiredArgsConstructor
public class CheckoutController {
    private final RentalFacade rentalFacade;

    @GetMapping
    public CommonResponse<Page<BookRentalInfo>> getOngoingRentals(@PageableDefault(page = 0, size = 10)
                                                                      Pageable pageable) {
        return CommonResponse.success(rentalFacade.getOngoingRentalInfo(pageable));
    }

    @GetMapping("/isbn/{isbn}")
    ResponseEntity<CommonResponse<RentalUsers>> getRentalUserList(@PathVariable String isbn){
        return ResponseEntity.ok().body(
                CommonResponse.success(rentalFacade.getRentalUserList(isbn)));
    }

    @PatchMapping("/id/{id}/renew")
    ResponseEntity<CommonResponse<String>> renewRental(@PathVariable Long id){
        rentalFacade.renew(id);
        return ResponseEntity.ok().body(CommonResponse.success(null, "Renew Completed"));
    }

    @PutMapping("/{rentalId}/return")
    public CommonResponse<Void> returnBook(@PathVariable Long rentalId) {
        rentalFacade.renew(rentalId);
        return CommonResponse.success();
    }


}
