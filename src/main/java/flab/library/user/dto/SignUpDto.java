package flab.library.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class SignUpDto {

	@Getter
	@AllArgsConstructor @Builder
	public static class Request{
		private String id;
		private String password;
		private String name;
	}

}
