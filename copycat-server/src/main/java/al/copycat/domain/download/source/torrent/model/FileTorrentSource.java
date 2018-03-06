package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.torrent.service.FileTorrentInspector;
import al.copycat.domain.download.source.torrent.service.TorrentInspector;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;

@Slf4j
@Getter
@AllArgsConstructor(staticName = "of")
public class FileTorrentSource implements TorrentSource<File> {

	private final File source;
	private final TorrentMetadata metadata;

	public static FileTorrentSource fromFile(File file) {
		try {
			TorrentInspector<File> inspector = FileTorrentInspector.create();
			TorrentMetadata metadata = inspector.getMetadata(file);
			return new FileTorrentSource(file, metadata);
		} catch (Exception e) {
			log.error("Invalid file: {}", file.getAbsolutePath(), e);
			throw new DownloadException("Invalid file: " + file.getAbsolutePath(), e);
		}
	}

}
