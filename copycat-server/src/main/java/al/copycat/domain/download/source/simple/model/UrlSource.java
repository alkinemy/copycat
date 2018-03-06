package al.copycat.domain.download.source.simple.model;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.common.model.Source;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
@Getter
@AllArgsConstructor(staticName = "of")
public class UrlSource implements Source<URL> {

	private URL source;

	public static UrlSource of(String source) {
		try {
			URL url = new URL(source);
			return new UrlSource(url);
		} catch (MalformedURLException e) {
			log.error("Invalid URL: {}", source, e);
			throw new DownloadException("Invalid URL: " + source, e);
		}
	}
}
