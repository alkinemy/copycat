package al.copycat.domain.download.source.torrent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.nio.file.Path;

@Getter
@AllArgsConstructor(staticName = "of")
public class FileTorrentSource implements TorrentSource<File> {

	private File source;
	private Path destination;
	private TorrentMetadata metadata;

}
