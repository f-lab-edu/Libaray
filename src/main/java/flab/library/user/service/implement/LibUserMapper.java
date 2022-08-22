package flab.library.user.service.implement;

import java.util.ArrayList;
import java.util.stream.Collectors;

import flab.library.book.domain.entity.Book;
import flab.library.rental.domain.entity.Rental;
import org.springframework.stereotype.Service;

import flab.library.user.domain.UserVO;
import flab.library.user.domain.entity.LibUser;
import flab.library.user.dto.SignUpDto;

@Service
public class LibUserMapper {

	private UserVO.UserBookVO toUserBookVO(Rental rental){
		Book book = rental.getBook();
		return UserVO.UserBookVO.builder()
				.title(book.getTitle())
				.isbn(book.getIsbn())
				.startDate(rental.getStartDate())
				.endDate(rental.getEndDate())
				.build();

	}

	public UserVO toVO(LibUser libUser){
		return UserVO.builder()
			.id(libUser.getId())
			.books(libUser.getRentalList().stream()
					.map(this::toUserBookVO)
					.collect(Collectors.toList()))
			.build();
	}

	public LibUser toEntityFromVO(UserVO userVO){
		return LibUser.builder()
			.id(userVO.getId())
			.build();

	}

	public LibUser toEntityFromSignUpDTO(SignUpDto.Request signUpDto){
		return LibUser.builder()
			.id(signUpDto.getId())
			.password(signUpDto.getPassword())
			.name(signUpDto.getName())
			.active(true)
			.build();
	}
}
