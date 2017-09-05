package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.source.common.model.Source;
import lombok.Getter;

import java.io.File;

@Getter
public class FileTorrentSource implements Source {

	private File source;

	private File destination;

}
