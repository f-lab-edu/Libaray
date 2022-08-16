package flab.library.user.domain.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
