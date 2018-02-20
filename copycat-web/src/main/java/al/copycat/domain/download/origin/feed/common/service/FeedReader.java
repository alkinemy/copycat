package al.copycat.domain.download.origin.feed.common.service;

import al.copycat.domain.download.origin.feed.common.model.Feed;
import al.copycat.domain.download.origin.feed.common.model.FeedEntry;

import java.util.List;

public interface FeedReader<S extends Feed, T extends FeedEntry> {

	List<T> read(S feed);

}
