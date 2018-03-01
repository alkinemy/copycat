package al.copycat.interfaces.api.download.controller;

import al.copycat.interfaces.api.download.dto.MultipartFileDownloadDto;
import al.copycat.interfaces.api.download.dto.TorrentDownloadDto;
import al.copycat.interfaces.api.download.dto.UrlDownloadDto;
import al.copycat.interfaces.api.download.service.DownloadApiFacadeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ManualDownloadController {

	private final DownloadApiFacadeService downloadApiFacadeService;

	@Autowired
	public ManualDownloadController(DownloadApiFacadeService downloadApiFacadeService) {
		this.downloadApiFacadeService = downloadApiFacadeService;
	}

	@PostMapping("/downloads/files")
	public void downloadFromFile(@ModelAttribute MultipartFileDownloadDto downloadDto) {
		log.debug("Download file: {}", downloadDto);
		downloadApiFacadeService.download(downloadDto)
			.block();
	}

	@PostMapping("/downloads/urls")
	public void downloadUrl(@RequestBody UrlDownloadDto downloadDto) {
		log.debug("Download url: {}", downloadDto);
		downloadApiFacadeService.download(downloadDto)
			.block();
	}

	@PostMapping("/downloads/torrents")
	public void downloadTorrent(@RequestBody TorrentDownloadDto downloadDto) {
		log.debug("Download torrent: {}", downloadDto);
		downloadApiFacadeService.download(downloadDto)
			.block();
	}

}
