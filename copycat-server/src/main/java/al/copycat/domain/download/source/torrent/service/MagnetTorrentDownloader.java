package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.common.service.Downloader;
import al.copycat.domain.download.source.torrent.model.MagnetTorrentSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Slf4j
@Service
public class MagnetTorrentDownloader implements Downloader<MagnetTorrentSource> {

	@Override
	public Path startDownload(MagnetTorrentSource magnet) {
		try {
			TorrentClient client = TorrentClient.fromMagnet(magnet);
			client.startDownload();
			return magnet.getDestination();
		} catch (Exception e) {
			log.error("Fail to start downloading magnet: {}", magnet.getSource(), e);
			throw new DownloadException("Fail to start downloading magnet", e);
		}
	}

}
