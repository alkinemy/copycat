package al.copycat.domain.download.direct.model;

import al.copycat.domain.download.common.model.Source;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
public class MultipartFileSource implements Source{

	private MultipartFile source;

	private File destination;

}
