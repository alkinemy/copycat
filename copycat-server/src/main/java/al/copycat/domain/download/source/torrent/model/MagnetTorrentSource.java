package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.torrent.exception.TorrentException;
import bt.magnet.MagnetUri;
import bt.magnet.MagnetUriParser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MagnetTorrentSource implements TorrentSource<String> {

	private String source;
	private TorrentMetadata metadata;

	public static MagnetTorrentSource fromMagnet(String source) {
		try {
			Inspector inspector = Inspector.create();
			TorrentMetadata metadata = inspector.getMetadata(source);
			return new MagnetTorrentSource(source, metadata);
		} catch (Exception e) {
			log.error("Invalid magnet: {}", source, e);
			throw new DownloadException("Invalid magnet: " + source, e);
		}
	}

	private static class Inspector {

		private final MagnetUriParser parser;

		private Inspector() {
			this.parser = MagnetUriParser.parser();
		}

		static Inspector create() {
			return new Inspector();
		}

		TorrentMetadata getMetadata(String magnet) {
			try {
				MagnetUri uri = parser.parse(magnet);
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

}
