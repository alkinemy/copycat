package al.copycat.domain.download.source.direct.model;

import al.copycat.domain.download.source.common.model.Source;
import lombok.Getter;

import java.io.File;
import java.net.URL;

@Getter
public class UrlSource implements Source<URL> {

	private URL source;

	private File destination;

}
