package al.copycat.domain.download.source.torrent.exception;

import al.copycat.domain.base.exception.CopycatException;

public class TorrentException extends CopycatException {

	public TorrentException(String message) {
		super(message);
	}

	public TorrentException(String message, Throwable cause) {
		super(message, cause);
	}

	public TorrentException(Throwable cause) {
		super(cause);
	}

	public TorrentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
