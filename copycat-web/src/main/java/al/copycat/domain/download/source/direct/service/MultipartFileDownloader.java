package al.copycat.domain.download.source.direct.service;

import al.copycat.domain.base.util.CompressionUtils;
import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.common.service.Downloader;
import al.copycat.domain.download.source.direct.model.MultipartFileSource;
import org.springframework.stereotype.Service;

@Service
public class MultipartFileDownloader implements Downloader<MultipartFileSource> {

	@Override
	public void startDownload(MultipartFileSource multipartFile) {
		try {
			multipartFile.getSource().transferTo(multipartFile.getDestination());

			if (CompressionUtils.isCompressedFile(multipartFile.getDestination())) {
				//FIXME 뭔가를 리턴하게 하는게 좋을듯
				CompressionUtils.uncompress(multipartFile.getDestination());
			}
		} catch (Exception e) {
			throw new DownloadException("Fail to start downloading multipart file: " + multipartFile.getSource().getOriginalFilename(), e);
		}
	}

}
