package al.copycat.domain.download.source.torrent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@Getter
@AllArgsConstructor(staticName = "of")
public class FileTorrentSource implements TorrentSource<File> {

	private File source;
	private TorrentMetadata metadata;

}
