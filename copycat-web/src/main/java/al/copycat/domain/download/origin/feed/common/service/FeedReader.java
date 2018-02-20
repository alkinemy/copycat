package al.copycat.domain.download.origin.feed.common.service;

import al.copycat.domain.download.origin.common.service.OriginReader;
import al.copycat.domain.download.origin.feed.common.model.Feed;
import al.copycat.domain.download.origin.feed.common.model.FeedEntry;

public interface FeedReader<S extends Feed, T extends FeedEntry> extends OriginReader<S, T> {
}
