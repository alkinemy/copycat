package al.copycat.domain.download.origin.common.service;

import al.copycat.domain.download.origin.common.model.Origin;
import al.copycat.domain.download.origin.common.model.OriginEntry;

import java.util.List;

public interface OriginReader<S extends Origin, T extends OriginEntry> {

	List<T> read(S origin);

}
