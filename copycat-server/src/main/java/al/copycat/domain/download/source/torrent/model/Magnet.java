package al.copycat.domain.download.source.torrent.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(staticName = "of")
public class Magnet {

	private final String url;

	@Override
	public String toString() {
		return url;
	}

}
