package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.torrent.service.TorrentInspectDelegateService;
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

	private URL source;
	private TorrentMetadata metadata;

	public static UrlTorrentSource fromUrl(String source, TorrentInspectDelegateService inspector) {
		try {
			URL url = new URL(source);
			TorrentMetadata metadata = inspector.getMetadata(url);
			return new UrlTorrentSource(url, metadata);
		} catch (MalformedURLException e) {
			log.error("Invalid URL: {}", source, e);
			throw new DownloadException("Invalid URL: " + source, e);
		}
	}

}
