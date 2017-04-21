package al.copycat.domain.download.direct.service;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.common.service.Downloader;
import al.copycat.domain.download.direct.model.UrlLink;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Service
public class UrlLinkDownloader implements Downloader<UrlLink> {

	@Override
	public void startDownload(UrlLink urlLink) {
		try (ReadableByteChannel byteChannel = Channels.newChannel(urlLink.getUrl().openStream()); FileOutputStream outputStream = new FileOutputStream(urlLink.getDestination())) {
			outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
		} catch (Exception e) {
			throw new DownloadException("Fail to start download: " + urlLink.getUrl(), e);
		}
	}

}
