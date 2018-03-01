package al.copycat.interfaces.api.download.service;

import al.copycat.config.DownloadProperties;
import al.copycat.domain.download.source.common.service.DownloadFacadeService;
import al.copycat.domain.download.source.simple.model.MultipartFileSource;
import al.copycat.domain.download.source.simple.model.UrlSource;
import al.copycat.domain.download.source.torrent.model.UrlTorrentSource;
import al.copycat.interfaces.api.download.dto.MultipartFileDownloadDto;
import al.copycat.interfaces.api.download.dto.TorrentDownloadDto;
import al.copycat.interfaces.api.download.dto.UrlDownloadDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.nio.file.Paths;

@Slf4j
@Service
public class DownloadApiFacadeService {

	private final DownloadFacadeService downloaderDelegateService;

	private final DownloadProperties downloadProperties;

	@Autowired
	public DownloadApiFacadeService(DownloadFacadeService downloaderDelegateService, DownloadProperties downloadProperties) {
		this.downloaderDelegateService = downloaderDelegateService;
		this.downloadProperties = downloadProperties;
	}

	public Mono<Void> download(MultipartFileDownloadDto downloadDto) {
		return Mono.fromCallable(() -> Paths.get(downloadProperties.getRoot(), downloadDto.getFile().getOriginalFilename()))
			.map(downloadPath -> MultipartFileSource.of(downloadDto.getFile(), downloadPath))
			.map(downloaderDelegateService::download)
			.then();
	}

	public Mono<Void> download(UrlDownloadDto downloadDto) {
		return Mono.fromCallable(() -> FilenameUtils.getName(downloadDto.getUrl()))
			.map(fileName -> Paths.get(downloadProperties.getRoot(), fileName))
			.map(downloadPath -> UrlSource.of(downloadDto.getUrl(), downloadPath))
			.map(downloaderDelegateService::download)
			.then();
	}

	public Mono<Void> download(TorrentDownloadDto downloadDto) {
		return Mono.fromCallable(() -> Paths.get(downloadProperties.getRoot()))
			.map(downloadRoot -> UrlTorrentSource.fromUrl(downloadDto.getTorrent(), downloadRoot))
			.map(downloaderDelegateService::download)
			.then();
	}

}
