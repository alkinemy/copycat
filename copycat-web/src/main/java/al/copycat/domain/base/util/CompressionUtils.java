package al.copycat.domain.base.util;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;

public abstract class CompressionUtils {

	public static void uncompress(File file) throws IOException, ArchiveException {
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
