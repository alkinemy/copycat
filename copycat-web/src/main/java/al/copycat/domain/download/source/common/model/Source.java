package al.copycat.domain.download.source.common.model;

import java.io.File;

public interface Source<T> {

	T getSource();

	File getDestination();

}
