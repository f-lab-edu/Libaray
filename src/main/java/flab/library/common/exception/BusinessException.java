package flab.library.common.exception;

public class BusinessException extends RuntimeException{
	private static final String PREFIX = "[Business Exception] ";

	private String reason = PREFIX;
	private String errorCode = "B000";

	private BusinessException(String reason, String errorCode){
		super(reason);
		this.reason = reason;
		this.errorCode = errorCode;
	}

	static public BusinessException create(BusinessExceptionDictionary businessException){
		return new BusinessException(PREFIX + businessException.getReason(), businessException.getCode());
	}
}
