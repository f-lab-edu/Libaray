package flab.library.user.domain.entity;

import flab.library.rental.domain.entity.Rental;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor @Builder
public class LibUser {

    @Id
    String id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    boolean active = true;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Rental> rentalList;

}
