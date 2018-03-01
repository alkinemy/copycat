package al.copycat.interfaces.api.download.controller;

import al.copycat.interfaces.api.download.service.DownloadFacadeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
public class ManualDownloadController {

	private final DownloadFacadeService downloadFacadeService;

	@Autowired
	public ManualDownloadController(DownloadFacadeService downloadFacadeService) {
		this.downloadFacadeService = downloadFacadeService;
	}

	@PostMapping("/downloads/files")
	public void downloadFromFile(@RequestParam("file") MultipartFile file) {
		log.debug("download file: {}", file.getOriginalFilename());
		downloadFacadeService.download(file)
			.block();
	}

	@PostMapping("/downloads/urls")
	public void downloadUrl(String url) {
		log.debug("download url: {}", url);
		downloadFacadeService.download(url)
			.block();
	}

}
