package al.copycat.domain.download.torrent.repository;

import al.copycat.domain.common.repository.CopycatRepository;
import al.copycat.domain.download.torrent.model.TorrentDownload;
import org.springframework.stereotype.Repository;

@Repository
public interface TorrentDownloadRepository extends CopycatRepository<TorrentDownload, String> {
}
