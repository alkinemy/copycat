package al.copycat.domain.download.source.torrent.model;

import lombok.Getter;

import java.nio.file.Path;

@Getter
public class MagnetTorrentSource implements TorrentSource<String> {

	private String source;
	private Path destination;
	private TorrentMetadata metadata;

}
