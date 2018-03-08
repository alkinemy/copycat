package al.copycat.domain.base.util;

import al.copycat.domain.base.exception.CopycatException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public abstract class FileUtils {

	public static Path createDirectories(Path path) {
		try {
			return Files.createDirectories(path);
		} catch (FileAlreadyExistsException ignored) {
			log.warn("File already exist, ignore exception: {}", path);
			return path;
		} catch (IOException e) {
			throw new CopycatException("Fail to create directories: " + path , e);
		}
	}

	public static Path createParentDirectories(Path path) {
		return createDirectories(path.getParent());
	}

	public static void deleteIfExists(Path path) {
		try {
			org.apache.commons.io.FileUtils.forceDelete(path.toFile());
		} catch (IOException e) {
			throw new CopycatException("Fail to delete file: " + path, e);
		}
	}

}
