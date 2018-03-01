package al.copycat.domain.download.source.common.model;

import java.nio.file.Path;

public interface Source<T> {

	T getSource();

	Path getDestination();

}
