package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.source.torrent.exception.TorrentException;
import al.copycat.domain.download.source.torrent.model.Magnet;
import al.copycat.domain.download.source.torrent.model.TorrentMetadata;
import bt.magnet.MagnetUri;
import bt.magnet.MagnetUriParser;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
public class MagnetTorrentInspector implements TorrentInspector<Magnet> {

	private final MagnetUriParser parser;

	private MagnetTorrentInspector() {
		this.parser = MagnetUriParser.parser();
	}

	public static MagnetTorrentInspector create() {
		return new MagnetTorrentInspector();
	}

	@Override
	public TorrentMetadata getMetadata(Magnet magnet) {
		try {
			MagnetUri uri = parser.parse(magnet.getUrl());
			return TorrentMetadata.builder()
				.id(uri.getTorrentId().toString())
				.name(uri.getDisplayName().orElse("MAGNET_NO_NAME_" + LocalDateTime.now().toString()))
				.build();
		} catch (Exception e) {
			log.error("Fail to inspect magnet: {}", magnet, e);
			throw new TorrentException("Fail to inspect magnet: " + magnet, e);
		}
	}

}
