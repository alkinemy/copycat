package al.copycat.domain.download.source.simple.model;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.common.model.Source;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;

@Slf4j
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlSource implements Source<URL> {

	private URL source;

	private Path destination;

	public static UrlSource of(String source, Path destination) {
		try {
			URL url = new URL(source);
			return new UrlSource(url, destination);
		} catch (MalformedURLException e) {
			log.error("Invalid URL: {}", source, e);
			throw new DownloadException("Invalid URL: " + source, e);
		}
	}
}
