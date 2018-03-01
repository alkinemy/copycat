package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.source.common.model.Source;
import lombok.Getter;

import java.io.File;
import java.nio.file.Path;

@Getter
public class FileTorrentSource implements Source<File> {

	private File source;

	private Path destination;

}
