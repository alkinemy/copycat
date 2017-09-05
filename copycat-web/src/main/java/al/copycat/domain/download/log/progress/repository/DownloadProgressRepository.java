package al.copycat.domain.download.log.progress.repository;

import al.copycat.domain.base.repository.CopycatRepository;
import al.copycat.domain.download.log.progress.model.DownloadProgress;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadProgressRepository extends CopycatRepository<DownloadProgress, Long> {
}
