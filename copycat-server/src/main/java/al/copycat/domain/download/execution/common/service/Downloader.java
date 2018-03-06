package al.copycat.domain.download.execution.common.service;

import al.copycat.domain.download.execution.common.model.DownloadForm;

import java.nio.file.Path;

public interface Downloader<T extends DownloadForm> {

	Path startDownload(T t);

}
