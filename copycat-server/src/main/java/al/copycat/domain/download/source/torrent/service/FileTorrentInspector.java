package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.source.torrent.model.TorrentMetadata;
import bt.metainfo.MetadataService;
import bt.metainfo.Torrent;

import java.net.URL;

public class FileTorrentInspector {

	private MetadataService metadataService;

	private FileTorrentInspector() {
		this.metadataService = new MetadataService();
	}

	public static FileTorrentInspector create() {
		return new FileTorrentInspector();
	}

	public TorrentMetadata getMetadata(URL url) {
		Torrent torrent = metadataService.fromUrl(url);
		return TorrentMetadata.builder()
			.name(torrent.getName())
			.size(torrent.getSize())
			.build();
	}

}
