package al.copycat.domain.download.source.torrent.repository;

import al.copycat.domain.base.repository.CopycatRepository;
import al.copycat.domain.download.source.torrent.model.TorrentDownload;
import org.springframework.stereotype.Repository;

@Repository
public interface TorrentDownloadRepository extends CopycatRepository<TorrentDownload, String> {
}
