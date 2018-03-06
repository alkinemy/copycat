package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.source.torrent.model.TorrentMetadata;
import bt.metainfo.MetadataService;

import java.net.URL;

public class MangetTorrentInspector {

	private MetadataService metadataService;

	private MangetTorrentInspector() {
		this.metadataService = new MetadataService();
	}

	public static MangetTorrentInspector create() {
		return new MangetTorrentInspector();
	}

	public TorrentMetadata getMetadata(URL url) {
		//FIXME: MangetUri 이용?
		return null;
	}

}
