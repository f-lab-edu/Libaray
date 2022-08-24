package flab.library.user.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class UpdatePwdDto {

	@ToString
	@Getter
	@NoArgsConstructor
	public static class Request{
		@Null
		private String id;

		@NotEmpty
		private String password;

		public void setId(String id) {
			this.id = id;
		}
	}

}
