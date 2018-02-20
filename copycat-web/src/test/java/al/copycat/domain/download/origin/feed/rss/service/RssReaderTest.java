package al.copycat.domain.download.origin.feed.rss.service;

import al.copycat.domain.download.origin.feed.rss.model.Rss;
import al.copycat.domain.download.origin.feed.rss.model.RssEntry;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RunWith(SpringRunner.class)
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
