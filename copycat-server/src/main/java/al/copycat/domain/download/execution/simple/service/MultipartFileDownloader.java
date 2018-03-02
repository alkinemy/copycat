package al.copycat.domain.download.execution.simple.service;

import al.copycat.domain.base.util.CompressionUtils;
import al.copycat.domain.base.util.FileUtils;
import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.execution.common.service.Downloader;
import al.copycat.domain.download.execution.simple.model.MultipartFileDownloadForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;

@Slf4j
@Service
public class MultipartFileDownloader implements Downloader<MultipartFileDownloadForm> {

	@Override
	public Path startDownload(MultipartFileDownloadForm downloadForm) {
		try {
			FileUtils.createDirectories(downloadForm.getDownloadTo());

			File destination = downloadForm.getDownloadTo().toFile();
			log.debug("Download file to: {}", destination);
			downloadForm.getFrom().getSource().transferTo(destination);

			if (CompressionUtils.isCompressedFile(destination)) {
				//FIXME 뭔가를 리턴하게 하는게 좋을듯
				CompressionUtils.uncompress(destination);
			}
			return downloadForm.getDownloadTo();
		} catch (Exception e) {
			throw new DownloadException("Fail to start downloading multipart file: " + downloadForm.getFrom().getSource().getOriginalFilename(), e);
		}
	}

}
