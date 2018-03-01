package al.copycat.domain.download.source.simple.service;

import al.copycat.domain.base.util.CompressionUtils;
import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.common.service.Downloader;
import al.copycat.domain.download.source.simple.model.MultipartFileSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.file.Path;

@Slf4j
@Service
public class MultipartFileDownloader implements Downloader<MultipartFileSource> {

	@Override
	public Path startDownload(MultipartFileSource multipartFileSource) {
		try {
			File destination = multipartFileSource.getDestination().toFile();
			log.debug("Download file to: {}", destination);
			multipartFileSource.getSource().transferTo(destination);

			if (CompressionUtils.isCompressedFile(destination)) {
				//FIXME 뭔가를 리턴하게 하는게 좋을듯
				CompressionUtils.uncompress(destination);
			}
			return multipartFileSource.getDestination();
		} catch (Exception e) {
			throw new DownloadException("Fail to start downloading multipart file: " + multipartFileSource.getSource().getOriginalFilename(), e);
		}
	}

}
