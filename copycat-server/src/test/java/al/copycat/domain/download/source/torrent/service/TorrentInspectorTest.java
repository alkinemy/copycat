package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.source.torrent.model.TorrentMetadata;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

//@RunWith(SpringRunner.class)
public class TorrentInspectorTest {

	@Test
	public void test() throws MalformedURLException {
		TorrentInspector torrentInspector = TorrentInspector.create();
		TorrentMetadata metadata = torrentInspector.getMetadata(
			new URL("magnet:?xt.1=urn:sha1:YNCKHTQCWBTRNJIV4WNAE52SJUQCZO5C&xt.2=urn:sha1:TXGCZQTH26NL6OUQAJJPFALHG2LTGBC7"));
		System.out.println(metadata);
	}

}