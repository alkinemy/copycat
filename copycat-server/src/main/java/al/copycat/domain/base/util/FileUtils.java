package al.copycat.domain.base.util;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public abstract class FileUtils extends org.apache.commons.io.FileUtils {

	public static Path createDirectories(Path path) throws IOException {
		try {
			return Files.createDirectories(path);
		} catch (FileAlreadyExistsException ignored) {
			log.warn("File already exist, ignore exception: {}", path);
			return path;
		}
	}

	public static Path createParentDirectories(Path path) throws IOException {
		return createDirectories(path.getParent());
	}

	public static void forceDelete(Path path) throws IOException {
		forceDelete(path.toFile());
	}

}
