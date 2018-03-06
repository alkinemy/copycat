package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.source.torrent.exception.TorrentException;
import al.copycat.domain.download.source.torrent.model.TorrentMetadata;
import bt.metainfo.MetadataService;
import bt.metainfo.Torrent;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.nio.file.Files;

@Slf4j
public class FileTorrentInspector implements TorrentInspector<File> {

	private final MetadataService metadataService;

	private FileTorrentInspector() {
		this.metadataService = new MetadataService();
	}

	public static FileTorrentInspector create() {
		return new FileTorrentInspector();
	}

	@Override
	public TorrentMetadata getMetadata(File file) {
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
