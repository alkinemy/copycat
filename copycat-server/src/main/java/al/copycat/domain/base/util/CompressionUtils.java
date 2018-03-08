package al.copycat.domain.base.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

@Slf4j
public abstract class CompressionUtils {

	public static Path uncompress(File file, Path uncompressTo, Charset charset) throws IOException {
		ZipInputStream zipInputStream = null;
		try {
			Path destination = FileUtils.createDirectories(uncompressTo);

			zipInputStream = new ZipInputStream(new FileInputStream(file), charset);
			ZipEntry zipEntry = zipInputStream.getNextEntry();
			while (zipEntry != null) {
				Path to = destination.resolve(zipEntry.getName());
				try (OutputStream outputStream = new FileOutputStream(to.toFile())) {
					IOUtils.copy(zipInputStream, outputStream);
				}
				zipEntry = zipInputStream.getNextEntry();
			}
			return destination;
		} catch (IOException e) {
			log.error("Fail to uncompress file: {}", file, e);
			try {
				FileUtils.forceDelete(uncompressTo);
			} catch (IOException ignored) {
				log.error("Fail to delete failed uncompressed directory: {}", uncompressTo, ignored);
			}
			throw e;
		} finally {
			if (zipInputStream != null) {
				try {
					zipInputStream.closeEntry();
					zipInputStream.close();
				} catch (Exception ignored) {
				}
			}
		}
	}

	public static Path uncompress(File file, Path uncompressTo) throws IOException {
		return uncompress(file, uncompressTo, StandardCharsets.UTF_8);
	}

	public static boolean isCompressed(File file) {
		try(ZipFile ignored = new ZipFile(file)) {
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

}
