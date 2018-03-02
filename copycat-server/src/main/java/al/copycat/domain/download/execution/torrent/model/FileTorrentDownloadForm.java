package al.copycat.domain.download.execution.torrent.model;

import al.copycat.domain.download.source.torrent.model.FileTorrentSource;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;

@Getter
@AllArgsConstructor(staticName = "of")
public class FileTorrentDownloadForm implements TorrentDownloadForm<FileTorrentSource> {

	private FileTorrentSource from;
	private Path torrentContentDownloadTo;

}
