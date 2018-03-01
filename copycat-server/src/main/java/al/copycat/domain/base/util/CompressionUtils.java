package al.copycat.domain.base.util;

import al.copycat.domain.base.exception.CopycatException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public abstract class CompressionUtils {

	private static final String COMPRESSED_FILE_TYPE = "application/x-zip-compressed";

	public static void uncompress(File file) throws IOException, ArchiveException {
		try (InputStream inputStream = new FileInputStream(file);
			ArchiveInputStream in = new ArchiveStreamFactory().createArchiveInputStream("zip", inputStream)) {

			ZipArchiveEntry entry = (ZipArchiveEntry) in.getNextEntry();
			//FIXME parent directory 이름 변경
			try (OutputStream out = new FileOutputStream(new File("", entry.getName()))) {
				IOUtils.copy(in, out);
			}

		}
	}

	public static boolean isCompressedFile(File file) {
		try {
			Path filePath = file.toPath();
			String fileType = Files.probeContentType(filePath);
			return COMPRESSED_FILE_TYPE.equals(fileType);
		} catch (Exception exception) {
			log.warn("Fail to check file type");
			throw new CopycatException("Fail to check file type", exception);
		}
	}

}
