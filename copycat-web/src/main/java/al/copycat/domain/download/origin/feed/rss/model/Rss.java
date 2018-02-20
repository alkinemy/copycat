package al.copycat.domain.download.origin.feed.rss.model;

import al.copycat.domain.download.origin.feed.common.model.Feed;
import lombok.Getter;

@Getter
public class Rss implements Feed {

	private String url;

	private Rss(String url) {
		this.url = url;
	}

	public static Rss fromUrl(String url) {
		return new Rss(url);
	}

}
