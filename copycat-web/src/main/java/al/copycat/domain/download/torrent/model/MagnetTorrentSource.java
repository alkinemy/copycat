package al.copycat.domain.download.torrent.model;

import al.copycat.domain.download.common.model.Source;
import lombok.Getter;

import java.io.File;

@Getter
public class MagnetTorrentSource implements Source {

	private File destination;

}
