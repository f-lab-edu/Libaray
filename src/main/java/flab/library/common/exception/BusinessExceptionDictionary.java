package flab.library.common.exception;

import lombok.Getter;

@Getter
public enum BusinessExceptionDictionary {
	UNKNOWN("Unknown", "B000"),
	CALC_LATE_FEE("Fail to calculate late fee", "R100"),
	INVALID_RENTAL_ID("Check rental Id", "R200"),
	BOOK_NOT_FOUND_EXCEPTION("Book not found", "B001"),
	RENTAL_NOT_FOUND_EXCEPTION("Rental not found", "B002"),
	DELETED_ENTITY_EXCEPTION("The entity has already been deleted.", "B101"),
	ALREADY_RENTAL_BOOK_EXCEPTION("This book has already rental.", "B102");

	private final String reason;
	private final String code;

	BusinessExceptionDictionary(final String reason, final String code){
		this.reason = reason;
		this.code = code;
	}


}
