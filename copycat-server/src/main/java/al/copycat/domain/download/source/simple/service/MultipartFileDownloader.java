package al.copycat.domain.download.source.simple.service;

import al.copycat.domain.base.util.CompressionUtils;
import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.source.common.service.Downloader;
import al.copycat.domain.download.source.simple.model.MultipartFileSource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MultipartFileDownloader implements Downloader<MultipartFileSource> {

	@Override
	public void startDownload(MultipartFileSource multipartFileSource) {
		try {
			File destination = multipartFileSource.getDestination().toFile();
			multipartFileSource.getSource().transferTo(destination);

			if (CompressionUtils.isCompressedFile(destination)) {
				//FIXME 뭔가를 리턴하게 하는게 좋을듯
				CompressionUtils.uncompress(destination);
			}
		} catch (Exception e) {
			throw new DownloadException("Fail to start downloading multipart file: " + multipartFileSource.getSource().getOriginalFilename(), e);
		}
	}

}
