package al.copycat.domain.download.execution.simple.service;

import al.copycat.domain.base.util.FileUtils;
import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.execution.common.service.Downloader;
import al.copycat.domain.download.execution.simple.model.UrlDownloadForm;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;

@Service
public class UrlDownloader implements Downloader<UrlDownloadForm> {

	@Override
	public Path startDownload(UrlDownloadForm downloadForm) {
		try (ReadableByteChannel byteChannel = Channels.newChannel(downloadForm.getFrom().getSource().openStream());
			FileOutputStream outputStream = new FileOutputStream(downloadForm.getDownloadTo().toFile())) {

			FileUtils.createDirectories(downloadForm.getDownloadTo());
			outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
			return downloadForm.getDownloadTo();
		} catch (Exception e) {
			throw new DownloadException("Fail to start downloading url: " + downloadForm.getFrom(), e);
		}
	}

}
