package al.copycat.domain.download.origin.common.exception;

import al.copycat.domain.base.exception.ErrorCode;
import al.copycat.domain.download.common.exception.DownloadException;

public class OriginException extends DownloadException {

	public OriginException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}

	public OriginException(String message, ErrorCode errorCode, Object[] args) {
		super(message, errorCode, args);
	}

	public OriginException(String message, ErrorCode errorCode, Throwable t, Object[] args) {
		super(message, errorCode, t, args);
	}

}
