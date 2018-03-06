package al.copycat.domain.download.execution.simple.model;

import al.copycat.domain.download.source.simple.model.UrlSource;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;

@Slf4j
@Getter
@AllArgsConstructor(staticName = "of")
public class UrlDownloadForm implements SimpleDownloadForm<UrlSource> {

	private UrlSource from;
	private Path downloadTo;

}
