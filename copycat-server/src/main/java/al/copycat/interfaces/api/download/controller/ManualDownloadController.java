package al.copycat.interfaces.api.download.controller;

import al.copycat.interfaces.api.download.dto.MagnetDownloadDto;
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
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class ManualDownloadController {

	private final DownloadApiFacadeService downloadApiFacadeService;

	@Autowired
	public ManualDownloadController(DownloadApiFacadeService downloadApiFacadeService) {
		this.downloadApiFacadeService = downloadApiFacadeService;
	}

	@PostMapping("/downloads/files")
	public Mono<Void> downloadFromFile(@ModelAttribute MultipartFileDownloadDto downloadDto) {
		log.debug("Download file: {}", downloadDto);
		return downloadApiFacadeService.download(downloadDto);
	}

	@PostMapping("/downloads/urls")
	public Mono<Void> downloadUrl(@RequestBody UrlDownloadDto downloadDto) {
		log.debug("Download url: {}", downloadDto);
		return downloadApiFacadeService.download(downloadDto);
	}

	@PostMapping("/downloads/torrents/files")
	public Mono<Void> downloadTorrent(@RequestBody TorrentDownloadDto downloadDto) {
		log.debug("Download torrent: {}", downloadDto);
		return downloadApiFacadeService.download(downloadDto);
	}

	@PostMapping("/downloads/torrents/magnets")
	public Mono<Void> downloadMagnet(@RequestBody MagnetDownloadDto downloadDto) {
		log.debug("Download magnet: {}", downloadDto);
		return downloadApiFacadeService.download(downloadDto);
	}

}
