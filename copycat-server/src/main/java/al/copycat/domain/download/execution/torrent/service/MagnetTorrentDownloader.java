package al.copycat.domain.download.execution.torrent.service;

import al.copycat.domain.base.util.FileUtils;
import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.execution.common.service.Downloader;
import al.copycat.domain.download.execution.torrent.model.MagnetTorrentDownloadForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Slf4j
@Service
public class MagnetTorrentDownloader implements Downloader<MagnetTorrentDownloadForm> {

	@Override
	public Path startDownload(MagnetTorrentDownloadForm downloadForm) {
		try {
			FileUtils.createDirectories(downloadForm.getTorrentContentDownloadTo());

			TorrentClient client = TorrentClient.fromMagnet(downloadForm);
			client.startDownload();
			return downloadForm.getTorrentContentDownloadTo();
		} catch (Exception e) {
			log.error("Fail to start downloading magnet: {}", downloadForm.getFrom().getSource(), e);
			throw new DownloadException("Fail to start downloading magnet", e);
		}
	}

}
