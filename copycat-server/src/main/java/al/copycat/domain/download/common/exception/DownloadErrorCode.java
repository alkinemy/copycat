package al.copycat.domain.download.common.exception;

import al.copycat.domain.base.exception.ErrorCode;
import al.copycat.domain.download.source.torrent.exception.TorrentException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DownloadErrorCode implements ErrorCode {

	E400_UNSUPPORTED_DOWNLOAD_TYPE,
	E400_INVALID_DOWNLOAD_REQUEST_PARAMETER,
	E400_INVALID_SOURCE_SIMPLE_URL,
	E400_INVALID_SOURCE_FILE_TORRENT,
	E400_INVALID_SOURCE_MAGNET_TORRENT,
	E400_INVALID_SOURCE_URL_TORRENT,

	E500_DOWNLOAD_FAILED_SIMPLE_MULTIPART_FILE,
	E500_DOWNLOAD_FAILED_SIMPLE_URL,
	E500_DOWNLOAD_FAILED_FILE_TORRENT,
	E500_DOWNLOAD_FAILED_MAGNET_TORRENT,
	E500_DOWNLOAD_FAILED_URL_TORRENT,

	E500_INSPECT_FAILED_FILE_TORRENT(TorrentException.class),
	E500_INSPECT_FAILED_MAGNET_TORRENT(TorrentException.class),
	E500_INSPECT_FAILED_URL_TORRENT(TorrentException.class),

	E500_TORRENT_CLIENT_START_FAILED,
	;

	@Getter
	private final Class<? extends DownloadException> exceptionClass;

	DownloadErrorCode() {
		this.exceptionClass = DownloadException.class;
	}

}
