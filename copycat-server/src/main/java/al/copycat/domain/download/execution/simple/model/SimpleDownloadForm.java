package al.copycat.domain.download.execution.simple.model;

import al.copycat.domain.download.execution.common.model.DownloadForm;
import al.copycat.domain.download.source.common.model.Source;

import java.nio.file.Path;

public interface SimpleDownloadForm<T extends Source<?>> extends DownloadForm<T> {

	Path getDownloadTo();

}
