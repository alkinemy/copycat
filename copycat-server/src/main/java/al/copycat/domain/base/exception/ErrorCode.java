package al.copycat.domain.base.exception;

/**
 * ErrorCode
 *
 * @author Milinae Kim
 * @version 1.0.0
 * @since 2018. 09. 07.
 */
public interface ErrorCode {

	Class<? extends CopycatException> getExceptionClass();

	String name();

}
