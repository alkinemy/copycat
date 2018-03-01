package al.copycat.domain.download.source.common.service;

import al.copycat.domain.download.source.common.model.Source;

import java.nio.file.Path;

public interface Downloader<T extends Source> {

	Path startDownload(T t);

}
