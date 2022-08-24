package flab.library.rental.contorller;

import flab.library.common.dto.CommonResponse;
import flab.library.rental.domain.RentalUsers;
import flab.library.rental.dto.BookRentalRequestDto;
import flab.library.rental.dto.BookRentalResponseDto;
import flab.library.rental.service.facade.RentalFacade;
import flab.library.user.domain.entity.LibUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static flab.library.rental.dto.BookRentalRequestDto.*;
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

    @PostMapping("/{bookId}")
    public ResponseEntity<CommonResponse<Long>> rentalBook(@PathVariable Long bookId,
                                                           @AuthenticationPrincipal LibUser user,
                                                           @RequestBody RentalRequest request) {
        return ResponseEntity.created(URI.create(""))
                .body(CommonResponse
                        .success(rentalFacade.rentalBook(user, bookId, request.getEndDate())));
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
