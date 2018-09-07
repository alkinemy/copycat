package al.copycat.domain.base.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * CommonErrorCode
 *
 * @author Milinae Kim
 * @version 1.0.0
 * @since 2018. 09. 07.
 */
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {

	E500_INTERNAL_SERVER_ERROR
	;

	@Getter
	private final Class<? extends CopycatException> exceptionClass;

	CommonErrorCode() {
		this.exceptionClass = CopycatException.class;
	}

}
