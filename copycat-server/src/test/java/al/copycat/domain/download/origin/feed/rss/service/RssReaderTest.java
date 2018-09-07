package al.copycat.domain.download.origin.feed.rss.service;

import al.copycat.domain.download.origin.feed.rss.model.Rss;
import al.copycat.domain.download.origin.feed.rss.model.RssEntry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = { RssReader.class, RestTemplate.class })
public class RssReaderTest {

	@Autowired
	private RssReader rssReader;

	@Test
	public void test() {
		Rss rss = Rss.fromUrl("https://rss.blog.naver.com/clova_ai.xml");
		List<RssEntry> entries = rssReader.read(rss);
		entries.forEach(System.out::println);
	}

}
