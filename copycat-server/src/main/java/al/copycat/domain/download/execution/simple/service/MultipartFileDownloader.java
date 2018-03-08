package al.copycat.domain.download.execution.simple.service;

import al.copycat.domain.base.util.CompressionUtils;
import al.copycat.domain.base.util.FileUtils;
import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.execution.common.service.Downloader;
import al.copycat.domain.download.execution.simple.model.MultipartFileDownloadForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Path;

@Slf4j
@Service
public class MultipartFileDownloader implements Downloader<MultipartFileDownloadForm> {

	private static final Charset ZIP_CHARSET = Charset.forName("CP949");

	@Override
	public Path startDownload(MultipartFileDownloadForm downloadForm) {
		try {
			FileUtils.createParentDirectories(downloadForm.getDownloadTo());

			File downloadTo = downloadForm.getDownloadTo().toFile();
			log.debug("Download file to: {}", downloadTo);
			downloadForm.getFrom().getSource().transferTo(downloadTo);

			if (CompressionUtils.isCompressed(downloadTo)) {
				String uncompressDirectory = downloadTo.getName() + ".uncompressed";
				Path uncompressTo = downloadTo.toPath().resolveSibling(uncompressDirectory);
				return CompressionUtils.uncompress(downloadTo, uncompressTo, ZIP_CHARSET);
			}
			return downloadForm.getDownloadTo();
		} catch (Exception e) {
			log.error("Fail to start downloading multipart file: {}", downloadForm.getFrom().getSource().getOriginalFilename(), e);
			throw new DownloadException("Fail to start downloading multipart file: " + downloadForm.getFrom().getSource().getOriginalFilename(), e);
		}
	}

}
