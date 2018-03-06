package al.copycat.domain.download.execution.simple.model;

import al.copycat.domain.download.source.simple.model.MultipartFileSource;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;

@Getter
@AllArgsConstructor(staticName = "of")
public class MultipartFileDownloadForm implements SimpleDownloadForm<MultipartFileSource> {

	private MultipartFileSource from;
	private Path downloadTo;

}
