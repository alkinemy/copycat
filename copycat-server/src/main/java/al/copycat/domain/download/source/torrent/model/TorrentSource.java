package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.source.common.model.Source;

public interface TorrentSource<T> extends Source<T> {

	String SUFFIX = ".torrent";
	String DEFAULT_DOWNLOAD_DIRECTORY_SUFFIX = ".download";

	TorrentMetadata getMetadata();

}
