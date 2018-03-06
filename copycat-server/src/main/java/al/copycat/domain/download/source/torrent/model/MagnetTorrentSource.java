package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.torrent.service.MagnetTorrentInspector;
import al.copycat.domain.download.source.torrent.service.TorrentInspector;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MagnetTorrentSource implements TorrentSource<Magnet> {

	private final Magnet source;
	private final TorrentMetadata metadata;

	public static MagnetTorrentSource fromMagnet(String source) {
		try {
			Magnet magnet = Magnet.of(source);
			TorrentInspector<Magnet> inspector = MagnetTorrentInspector.create();
			TorrentMetadata metadata = inspector.getMetadata(magnet);
			return new MagnetTorrentSource(magnet, metadata);
		} catch (Exception e) {
			log.error("Invalid magnet: {}", source, e);
			throw new DownloadException("Invalid magnet: " + source, e);
		}
	}

}
