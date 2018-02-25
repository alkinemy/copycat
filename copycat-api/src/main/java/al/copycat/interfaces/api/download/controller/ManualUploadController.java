package al.copycat.interfaces.api.download.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ManualUploadController {

	@PostMapping("/uploads/files")
	public void upload(@RequestParam("file") MultipartFile file) {
	}

}
