package al.copycat.domain.download.source.torrent.model;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.torrent.exception.TorrentException;
import bt.metainfo.MetadataService;
import bt.metainfo.Torrent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Files;

@Slf4j
@Getter
@AllArgsConstructor(staticName = "of")
public class FileTorrentSource implements TorrentSource<File> {

	private File source;
	private TorrentMetadata metadata;

	public static FileTorrentSource fromFile(File file) {
		try {
			Inspector inspector = Inspector.create();
			TorrentMetadata metadata = inspector.getMetadata(file);
			return new FileTorrentSource(file, metadata);
		} catch (Exception e) {
			log.error("Invalid file: {}", file.getAbsolutePath(), e);
			throw new DownloadException("Invalid file: " + file.getAbsolutePath(), e);
		}
	}


	private static class Inspector {

		private final MetadataService metadataService;

		private Inspector() {
			this.metadataService = new MetadataService();
		}

		static Inspector create() {
			return new Inspector();
		}

		TorrentMetadata getMetadata(File file) {
			try {
				byte[] fileBytes = Files.readAllBytes(file.toPath());
				Torrent torrent = metadataService.fromByteArray(fileBytes);
				return TorrentMetadata.builder()
					.name(torrent.getName())
					.size(torrent.getSize())
					.build();
			} catch (Exception e) {
				log.error("Fail to inspect torrent file: {}", file.getAbsolutePath(), e);
				throw new TorrentException("Fail to inspect torrent file: " + file.getAbsolutePath(), e);
			}
		}

	}

}
