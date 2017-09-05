package al.copycat.domain.download.source.direct.service;

import al.copycat.domain.download.common.exception.DownloadException;
import al.copycat.domain.download.common.service.Downloader;
import al.copycat.domain.download.source.direct.model.MultipartFileSource;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;

@Service
public class MultipartFileDownloader implements Downloader<MultipartFileSource> {

	@Override
	public void startDownload(MultipartFileSource multipartFile) {
		try {
			multipartFile.getSource().transferTo(multipartFile.getDestination());

			String fileType = Files.probeContentType(multipartFile.getDestination().toPath());
			if ("application/x-zip-compressed".equals(fileType)) {
				//uncompress;
			}
		} catch (Exception e) {
			throw new DownloadException("Fail to start downloading multipart file: " + multipartFile.getSource().getOriginalFilename(), e);
		}
	}

	private void uncompress(File file) throws IOException, ArchiveException {
		try (InputStream is = new FileInputStream(file);
			ArchiveInputStream in = new ArchiveStreamFactory().createArchiveInputStream("zip", is)) {

			ZipArchiveEntry entry = (ZipArchiveEntry) in.getNextEntry();
			//FIXME parent directory 이름 변경
			try (OutputStream out = new FileOutputStream(new File("", entry.getName()))) {
				IOUtils.copy(in, out);
			}

		}
	}

}
