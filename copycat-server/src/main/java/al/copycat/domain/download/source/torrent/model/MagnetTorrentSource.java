package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.torrent.service.TorrentInspectDelegateService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MagnetTorrentSource implements TorrentSource<String> {

	private String source;
	private TorrentMetadata metadata;

	public static MagnetTorrentSource fromMagnet(String source, TorrentInspectDelegateService inspector) {
		try {
			TorrentMetadata metadata = inspector.getMetadata(source);
			return new MagnetTorrentSource(source, metadata);
		} catch (Exception e) {
			log.error("Invalid magnet: {}", source, e);
			throw new DownloadException("Invalid magnet: " + source, e);
		}
	}

}
