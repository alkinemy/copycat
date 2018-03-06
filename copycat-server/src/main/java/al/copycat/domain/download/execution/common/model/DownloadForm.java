package al.copycat.domain.download.execution.common.model;

import al.copycat.domain.download.source.common.model.Source;

public interface DownloadForm<T extends Source<?>> {

	T getFrom();

}
