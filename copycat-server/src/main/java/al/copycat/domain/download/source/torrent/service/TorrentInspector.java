package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.source.torrent.model.TorrentMetadata;

public interface TorrentInspector<T> {

	TorrentMetadata getMetadata(T t);

}
