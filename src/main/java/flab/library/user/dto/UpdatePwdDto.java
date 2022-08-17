package flab.library.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UpdatePwdDto {

	@Getter
	@NoArgsConstructor
	public static class Request{
		private String id;
		private String password;

		@Builder
		public Request(String password){
			this.password = password;
		}

		public void setId(String id) {
			this.id = id;
		}
	}

}
