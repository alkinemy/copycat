package al.copycat.domain.download.source.torrent.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TorrentMetadata {

	private String name;
	private long size;

}
