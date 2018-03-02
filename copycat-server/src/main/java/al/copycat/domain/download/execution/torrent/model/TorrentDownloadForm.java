package al.copycat.domain.download.execution.torrent.model;

import al.copycat.domain.download.execution.common.model.DownloadForm;
import al.copycat.domain.download.source.torrent.model.TorrentSource;

import java.nio.file.Path;

public interface TorrentDownloadForm<T extends TorrentSource<?>> extends DownloadForm<T> {

	String TORRENT_FILE_SUFFIX = ".torrent";
	String TORRENT_CONTENT_DOWNLOAD_DIRECTORY_SUFFIX = ".download";

//	Path getTorrentFileDownloadTo();
	Path getTorrentContentDownloadTo();

}
