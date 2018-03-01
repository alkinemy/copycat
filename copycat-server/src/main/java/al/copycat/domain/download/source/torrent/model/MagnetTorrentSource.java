package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.source.common.model.Source;
import lombok.Getter;

import java.nio.file.Path;

@Getter
public class MagnetTorrentSource implements Source<String> {

	private String source;

	private Path destination;

}
