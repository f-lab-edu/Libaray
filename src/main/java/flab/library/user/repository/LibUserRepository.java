package flab.library.user.repository;

import flab.library.user.domain.entity.LibUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LibUserRepository extends JpaRepository<LibUser, String> {
    @Query(value = "select libUser " +
            "from LibUser libUser join fetch Rental rental " +
            "on libUser.id = rental.user " +
            "where libUser.id = :id")
    Optional<LibUser> findLibUserByIdFetchRental(String id);
}
