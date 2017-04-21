package al.copycat.domain.download.direct.model;

import al.copycat.domain.download.common.model.Source;
import lombok.Getter;

import java.io.File;
import java.net.URL;

@Getter
public class UrlLink implements Source {

	private URL url;

	private File destination;

}
