package al.copycat.domain.download.source.torrent.model;

import lombok.Getter;

@Getter
public class MagnetTorrentSource implements TorrentSource<String> {

	private String source;
	private TorrentMetadata metadata;

}
