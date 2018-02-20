package al.copycat.domain.download.origin.rss.model;

import al.copycat.domain.download.origin.common.model.Origin;
import lombok.Getter;

@Getter
public class Rss implements Origin {

	private String url;

	private Rss(String url) {
		this.url = url;
	}

	public static Rss fromUrl(String url) {
		return new Rss(url);
	}

}
