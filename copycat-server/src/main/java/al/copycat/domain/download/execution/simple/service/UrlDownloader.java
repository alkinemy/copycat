package al.copycat.domain.download.execution.simple.service;

import al.copycat.domain.base.util.CompressionUtils;
import al.copycat.domain.base.util.FileUtils;
import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.execution.common.service.Downloader;
import al.copycat.domain.download.execution.simple.model.UrlDownloadForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Path;

@Slf4j
@Service
public class UrlDownloader implements Downloader<UrlDownloadForm> {

	private static final Charset ZIP_CHARSET = Charset.forName("CP949");

	@Override
	public Path startDownload(UrlDownloadForm downloadForm) {
		try {
			FileUtils.createParentDirectories(downloadForm.getDownloadTo());

			File downloadTo = downloadForm.getDownloadTo().toFile();
			try (ReadableByteChannel byteChannel = Channels.newChannel(downloadForm.getFrom().getSource().openStream());
				FileOutputStream outputStream = new FileOutputStream(downloadTo)) {

				outputStream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
			}

			if (CompressionUtils.isCompressed(downloadTo)) {
				String uncompressDirectory = downloadTo.getName() + ".uncompressed";
				Path uncompressTo = downloadTo.toPath().resolveSibling(uncompressDirectory);
				return CompressionUtils.uncompress(downloadTo, uncompressTo, ZIP_CHARSET);
			}
			return downloadForm.getDownloadTo();
		} catch (Exception e) {
			log.error("Fail to start downloading url: {}", downloadForm.getFrom(), e);
			throw new DownloadException("Fail to start downloading url: " + downloadForm.getFrom(), e);
		}
	}

}
