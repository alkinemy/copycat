package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.torrent.service.TorrentInspector;
import al.copycat.domain.download.source.torrent.service.UrlTorrentInspector;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlTorrentSource implements TorrentSource<URL> {

	private final URL source;
	private final TorrentMetadata metadata;

	public static UrlTorrentSource fromUrl(String source) {
		try {
			URL url = new URL(source);
			TorrentInspector<URL> inspector = UrlTorrentInspector.create();
			TorrentMetadata metadata = inspector.getMetadata(url);
			return new UrlTorrentSource(url, metadata);
		} catch (MalformedURLException e) {
			log.error("Invalid URL: {}", source, e);
			throw new DownloadException("Invalid URL: " + source, e);
		}
	}

}
