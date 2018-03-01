package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.source.torrent.model.TorrentMetadata;
import bt.metainfo.MetadataService;
import bt.metainfo.Torrent;

import java.net.URL;

public class TorrentInspector {

	private MetadataService metadataService;

	public TorrentInspector() {
		this.metadataService = new MetadataService();
	}

	public TorrentMetadata getMetadata(URL url) {
		Torrent torrent = metadataService.fromUrl(url);
		return TorrentMetadata.builder()
			.name(torrent.getName())
			.size(torrent.getSize())
			.build();
	}

}
