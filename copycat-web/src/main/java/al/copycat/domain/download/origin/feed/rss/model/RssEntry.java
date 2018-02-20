package al.copycat.domain.download.origin.feed.rss.model;

import al.copycat.domain.download.origin.feed.common.model.Feed;
import com.rometools.rome.feed.synd.SyndEntry;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RssEntry implements Feed {

	private String title;
	private String author;
	private String description;
	private String link;

	private RssEntry(String title, String author, String description, String link) {
		this.title = title;
		this.author = author;
		this.description = description;
		this.link = link;
	}

	public static RssEntry fromSyndEntry(SyndEntry syndEntry) {
		return new RssEntry(syndEntry.getTitle(), syndEntry.getAuthor(), syndEntry.getDescription().getValue(), syndEntry.getLink());
	}

}
