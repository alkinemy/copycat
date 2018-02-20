package al.copycat.domain.download.source.torrent.exception;

public class TorrentException extends RuntimeException {

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
