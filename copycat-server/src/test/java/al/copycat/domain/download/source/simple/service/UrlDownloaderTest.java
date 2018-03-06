package al.copycat.domain.download.source.simple.service;

import bt.metainfo.MetadataService;
import bt.metainfo.Torrent;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UrlDownloaderTest {

	@Test
	public void test() {
		RestTemplate restTemplate = new RestTemplate();
		String str = restTemplate.getForObject("https://anidex.info/dl/124137", String.class);
		System.out.println(str);
	}

	@Test
	public void test2() throws MalformedURLException {
//		String url = "http://leopard-raws.org/download.php?hash=ac0ba762d8bf3fd7a9e059be2ac8449c";
		String url = "https://dl.dmhy.org/2018/03/01/b6dcb1cb78d61acc05490f4587aa5bca6cb33c4e.torrent";
		MetadataService metadataService = new MetadataService();
		Torrent torrent = metadataService.fromUrl(new URL(url));
		System.out.println(torrent);
	}

	@Test
	public void test3() throws IOException {
		byte[] bytes = Files.readAllBytes(Paths.get("/tmp/download.php?hash=ac0ba762d8bf3fd7a9e059be2ac8449c"));
		MetadataService metadataService = new MetadataService();
		Torrent torrent = metadataService.fromByteArray(bytes);
		System.out.println(torrent);
	}

}