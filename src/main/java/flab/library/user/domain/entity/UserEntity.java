package flab.library.user.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserEntity {

    @Id
    String id;

    @Column
    String name;

    @Column
    String password;

}
