package al.copycat.domain.download.source.torrent.model;

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

	public static MagnetTorrentSource fromMagnet(String source) {
		//FIXME use MagnetTorrentInspector
		return new MagnetTorrentSource(source, null);
	}

}
