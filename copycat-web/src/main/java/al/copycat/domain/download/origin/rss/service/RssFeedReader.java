package al.copycat.domain.download.origin.rss.service;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.origin.rss.model.Rss;
import al.copycat.domain.download.origin.rss.model.RssFeed;
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
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class RssFeedReader {

	private final RestTemplate restTemplate;

	@Autowired
	public RssFeedReader(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private List<RssFeed> read(Rss rss) {
		ResponseEntity<Resource> responseEntity = restTemplate.exchange(rss.getUrl(), HttpMethod.GET, null, Resource.class);
		try (InputStream inputStream = responseEntity.getBody().getInputStream()) {
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(inputStream));
			//FIXME feed mapping
			return Collections.emptyList();
		} catch (Exception e) {
			log.error("Fail to read feed", e);
			throw new DownloadException("Fail to read feed", e);
		}
	}
}
