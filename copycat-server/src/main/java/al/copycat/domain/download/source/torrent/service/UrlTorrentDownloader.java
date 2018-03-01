package al.copycat.domain.download.source.torrent.service;

import al.copycat.domain.base.util.FileUtils;
import al.copycat.domain.download.source.common.service.Downloader;
import al.copycat.domain.download.source.simple.model.UrlSource;
import al.copycat.domain.download.source.simple.service.UrlDownloader;
import al.copycat.domain.download.source.torrent.model.FileTorrentSource;
import al.copycat.domain.download.source.torrent.model.UrlTorrentSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
public class UrlTorrentDownloader implements Downloader<UrlTorrentSource> {

	private final UrlDownloader urlDownloader;

	private final FileTorrentDownloader fileTorrentDownloader;

	@Autowired
	public UrlTorrentDownloader(UrlDownloader urlDownloader,
		FileTorrentDownloader fileTorrentDownloader) {
		this.urlDownloader = urlDownloader;
		this.fileTorrentDownloader = fileTorrentDownloader;
	}

	@Override
	public Path startDownload(UrlTorrentSource source) {
		UrlSource urlSource = UrlSource.of(source.getSource(), source.getTorrentFileDestination());
		Path fileTorrentPath = urlDownloader.startDownload(urlSource);
		Path torrentDownloadPath = FileUtils.createDirectories(source.getTorrentDownloadDestination());
		FileTorrentSource fileTorrentSource = FileTorrentSource.of(fileTorrentPath.toFile(), torrentDownloadPath, source.getMetadata());
		return fileTorrentDownloader.startDownload(fileTorrentSource);
	}

}
