package al.copycat.domain.download.source.torrent.exception;

import al.copycat.domain.base.exception.CopycatException;
import al.copycat.domain.base.exception.ErrorCode;
import al.copycat.domain.download.common.exception.DownloadException;

public class TorrentException extends DownloadException {

	public TorrentException(String message, ErrorCode errorCode) {
		super(message, errorCode);
	}

	public TorrentException(String message, ErrorCode errorCode, Object[] args) {
		super(message, errorCode, args);
	}

	public TorrentException(String message, ErrorCode errorCode, Throwable t, Object[] args) {
		super(message, errorCode, t, args);
	}

}
