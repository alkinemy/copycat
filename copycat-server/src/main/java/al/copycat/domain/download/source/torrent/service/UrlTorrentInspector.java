package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.source.torrent.exception.TorrentException;
import al.copycat.domain.download.source.torrent.model.TorrentMetadata;
import bt.metainfo.MetadataService;
import bt.metainfo.Torrent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URL;

@Slf4j
@Component
public class UrlTorrentInspector implements TorrentInspector<URL> {

	private final MetadataService metadataService;

	private UrlTorrentInspector() {
		this.metadataService = new MetadataService();
	}

	public TorrentMetadata getMetadata(URL url) {
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
