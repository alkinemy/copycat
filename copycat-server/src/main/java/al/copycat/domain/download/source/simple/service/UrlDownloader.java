package al.copycat.domain.download.source.simple.service;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.common.service.Downloader;
import al.copycat.domain.download.source.simple.model.UrlSource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

@Service
public class UrlDownloader implements Downloader<UrlSource> {

	@Override
	public void startDownload(UrlSource url) {
		try (ReadableByteChannel byteChannel = Channels.newChannel(url.getSource().openStream());
			FileOutputStream outputStream = new FileOutputStream(url.getDestination().toFile())) {

			outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
		} catch (Exception e) {
			throw new DownloadException("Fail to start downloading url: " + url.getSource(), e);
		}
	}

}
