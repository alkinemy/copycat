package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.torrent.exception.TorrentException;
import bt.metainfo.MetadataService;
import bt.metainfo.Torrent;
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

	public static UrlTorrentSource fromUrl(String source) {
		try {
			URL url = new URL(source);
			Inspector inspector = Inspector.create();
			TorrentMetadata metadata = inspector.getMetadata(url);
			return new UrlTorrentSource(url, metadata);
		} catch (MalformedURLException e) {
			log.error("Invalid URL: {}", source, e);
			throw new DownloadException("Invalid URL: " + source, e);
		}
	}


	private static class Inspector {

		private final MetadataService metadataService;

		private Inspector() {
			this.metadataService = new MetadataService();
		}

		static Inspector create() {
			return new Inspector();
		}

		TorrentMetadata getMetadata(URL url) {
			try {
				Torrent torrent = metadataService.fromUrl(url);
				return TorrentMetadata.builder()
					.name(torrent.getName())
					.size(torrent.getSize())
					.build();
			} catch (Exception e) {
				log.error("Fail to inspect torrent url: {}", url, e);
				throw new TorrentException("Fail to inspect torrent url: " + url, e);
			}
		}

	}

}
