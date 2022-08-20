package flab.library.rental.domain.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

@Getter
@Entity
public class Rental {
    /**
     * 독립적인 식별자는 필요 없을지 추후 논의하고 싶은 부분이 있습니다.
     * 추가적으로 전부 참조 관계로 유지해도 될지도 논의해보고 싶습니다.
     */
    @Id
    @EmbeddedId
    RentalId rentalId;

    private boolean isReturn;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;
}
