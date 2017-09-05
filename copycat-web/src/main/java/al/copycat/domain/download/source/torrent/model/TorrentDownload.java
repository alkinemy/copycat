package al.copycat.domain.download.source.torrent.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class TorrentDownload {

	@Id
	private String id;

	private Float progress = 0.f;

}
