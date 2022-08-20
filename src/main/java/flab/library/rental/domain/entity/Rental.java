package flab.library.rental.domain.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rental {
    @Id
    @EmbeddedId
    RentalId rentalId;



}
