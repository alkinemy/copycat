package al.copycat.domain.download.source.torrent.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class TorrentMetadata {

	private String id;
	private String name;
	private long size;

}
