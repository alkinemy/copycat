package al.copycat.domain.base.exception;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

/**
 * Exceptions
 *
 * @author Milinae Kim
 * @version 1.0.0
 * @since 2018. 09. 07.
 */
@Slf4j
public abstract class Exceptions {

	private static final String DEFAULT_ERROR_MESSAGE = "Internal server error";
	private static final ErrorCode DEFAULT_ERROR_CODE = CommonErrorCode.E500_INTERNAL_SERVER_ERROR;

	public static void throwsException(boolean condition, ErrorCode errorCode, Object... args) {
		Objects.requireNonNull(errorCode);
		if (condition) {
			throw newException(errorCode, args);
		}
	}

	public static void throwsExceptionIfNull(Object object, ErrorCode errorCode, Object... args) {
		Exceptions.throwsException(object == null, errorCode, args);
	}

	public static void throwsExceptionIfNotNull(Object object, ErrorCode errorCode, Object... args) {
		Exceptions.throwsException(object != null, errorCode, args);
	}

	public static void throwsException(boolean condition, CopycatException e) {
		Objects.requireNonNull(e);
		if (condition) {
			throw e;
		}
	}

	public static void throwsExceptionIfNull(Object object, CopycatException e) {
		Exceptions.throwsException(object == null, e);
	}

	public static CopycatException newException(ErrorCode errorCode, Throwable t, Object... args) {
		return newException(errorCode.name(), errorCode, t, args);
	}

	public static CopycatException newException(String message, ErrorCode errorCode, Throwable t, Object... args) {
		try {
			return newExceptionInstance(message, errorCode, t, args);
		} catch (Exception e) {
			log.error("Exception create failed.", e);
			return new CopycatException(DEFAULT_ERROR_MESSAGE, DEFAULT_ERROR_CODE, t, null);
		}
	}

	private static CopycatException newExceptionInstance(String message, ErrorCode errorCode, Throwable t, Object... args)
		throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

		Class<? extends CopycatException> exceptionClass = errorCode.getExceptionClass();
		Constructor<? extends CopycatException> constructor = exceptionClass.getConstructor(String.class, ErrorCode.class, Throwable.class, Object[].class);
		return constructor.newInstance(message, errorCode, t, args);
	}

	public static CopycatException newException(ErrorCode errorCode, Object... args) {
		return newException(errorCode.name(), errorCode, args);
	}

	public static CopycatException newException(String message, ErrorCode errorCode, Object... args) {
		try {
			return newExceptionInstance(message, errorCode, args);
		} catch (Exception e) {
			log.error("Exception create failed.", e);
			return new CopycatException(DEFAULT_ERROR_MESSAGE, DEFAULT_ERROR_CODE);
		}
	}

	private static CopycatException newExceptionInstance(String message, ErrorCode errorCode, Object... args)
		throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

		Class<? extends CopycatException> exceptionClass = errorCode.getExceptionClass();
		Constructor<? extends CopycatException> constructor = exceptionClass.getConstructor(String.class, ErrorCode.class, Object[].class);
		return constructor.newInstance(message, errorCode, args);
	}

}
