package al.copycat.domain.download.origin.feed.rss.service;

import al.copycat.domain.base.exception.Exceptions;
import al.copycat.domain.download.common.exception.DownloadErrorCode;
import al.copycat.domain.download.origin.feed.common.service.FeedReader;
import al.copycat.domain.download.origin.feed.rss.model.Rss;
import al.copycat.domain.download.origin.feed.rss.model.RssEntry;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RssReader implements FeedReader<Rss, RssEntry> {

	private final RestTemplate restTemplate;

	@Autowired
	public RssReader(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public List<RssEntry> read(Rss rss) {
		ResponseEntity<Resource> responseEntity = restTemplate.exchange(rss.getUrl(), HttpMethod.GET, null, Resource.class);
		try (InputStream inputStream = responseEntity.getBody().getInputStream()) {
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(inputStream));
			List<SyndEntry> entries = feed.getEntries();
			return entries.stream()
				.map(RssEntry::fromSyndEntry)
				.collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Fail to read feed", e);
			throw Exceptions.newException(DownloadErrorCode.E500_FEED_READING_FAILED, e);
		}
	}

}
