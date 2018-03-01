package al.copycat.interfaces.api.download.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class MultipartFileDownloadDto {

	private MultipartFile file;

	@Override
	public String toString() {
		return file.getOriginalFilename();
	}

}
