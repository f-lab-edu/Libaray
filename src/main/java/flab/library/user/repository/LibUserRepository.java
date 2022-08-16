package flab.library.user.repository;

import flab.library.user.domain.entity.LibUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibUserRepository extends JpaRepository<LibUser, String> {

}
