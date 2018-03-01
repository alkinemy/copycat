package al.copycat.domain.download.source.simple.model;

import al.copycat.domain.download.source.common.model.Source;
import lombok.Getter;

import java.net.URL;
import java.nio.file.Path;

@Getter
public class UrlSource implements Source<URL> {

	private URL source;

	private Path destination;

}
