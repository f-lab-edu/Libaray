package flab.library.user.dto;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class SignUpDto {

	@Getter
	@AllArgsConstructor @Builder
	public static class Request{
		@NotEmpty
		private String id;
		@NotEmpty
		private String password;
		@NotEmpty
		private String name;
	}

}
