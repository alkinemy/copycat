package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.torrent.service.TorrentInspector;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlTorrentSource implements TorrentSource<URL> {

	private URL source;
	private Path torrentFileDestination;
	private Path torrentDownloadDestination;
	private TorrentMetadata metadata;

	public Path getDestination() {
		return getTorrentDownloadDestination();
	}

	public static UrlTorrentSource fromUrl(String source, Path downloadRoot) {
		try {
			URL url = new URL(source);
			TorrentInspector inspector = new TorrentInspector();
			TorrentMetadata metadata = inspector.getMetadata(url);
			Path torrentFileDestination = Paths.get(downloadRoot.toString(), metadata.getName() + SUFFIX);
			Path torrentDownloadDestination = Paths.get(downloadRoot.toString(), metadata.getName() + DEFAULT_DOWNLOAD_DIRECTORY_SUFFIX);
			return new UrlTorrentSource(url, torrentFileDestination, torrentDownloadDestination, metadata);
		} catch (MalformedURLException e) {
			log.error("Invalid URL: {}", source, e);
			throw new DownloadException("Invalid URL: " + source, e);
		}
	}

}
