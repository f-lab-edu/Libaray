package flab.library.rental.domain.entity;

import flab.library.book.domain.entity.Book;
import flab.library.user.domain.entity.LibUser;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class RentalId implements Serializable {

    @ManyToOne(targetEntity = LibUser.class)
    @JoinColumn(name="user_id")
    LibUser user;

    @ManyToOne(targetEntity = Book.class)
    @JoinColumn(name="book_id")
    Book book;
}
