package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.source.torrent.exception.TorrentException;
import al.copycat.domain.download.source.torrent.model.TorrentMetadata;
import bt.magnet.MagnetUri;
import bt.magnet.MagnetUriParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MagnetTorrentInspector implements TorrentInspector<String> {

	private final MagnetUriParser parser;

	public MagnetTorrentInspector() {
		this.parser = MagnetUriParser.parser();
	}

	public TorrentMetadata getMetadata(String magnet) {
		try {
			MagnetUri uri = parser.parse(magnet);
			return TorrentMetadata.builder()
				.name(uri.getDisplayName().orElse(StringUtils.EMPTY))
				.build();
		} catch (Exception e) {
			log.error("Fail to inspect magnet: {}", magnet, e);
			throw new TorrentException("Fail to inspect magnet: " + magnet, e);
		}
	}

}
