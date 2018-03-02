package al.copycat.domain.download.execution.torrent.model;

import al.copycat.domain.download.source.torrent.model.MagnetTorrentSource;
import lombok.Getter;

import java.nio.file.Path;

@Getter
public class MagnetTorrentDownloadForm implements TorrentDownloadForm<MagnetTorrentSource> {

	private MagnetTorrentSource from;
	private Path torrentContentDownloadTo;

}
