package al.copycat.domain.download.common.service;

import al.copycat.domain.download.source.common.model.Source;

public interface Downloader<T extends Source> {

	void startDownload(T t);

}
